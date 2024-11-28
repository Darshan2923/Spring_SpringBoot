package com.stream.app.wizstream.controller;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
// import org.apache.catalina.connector.Response;
import org.springframework.core.io.FileSystemResource;
// import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.stream.app.wizstream.model.Video;
import com.stream.app.wizstream.services.VideoService;
import com.stream.app.wizstream.utils.AppConstants;
import com.stream.app.wizstream.utils.CustomMessage;

@RestController
@RequestMapping("/api/v1/videos")
// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*",
// allowCredentials = "true")
public class VideoController {

    private VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // video upload
    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());
        Video savedVideo = videoService.save(video, file);
        System.out.println("Request received for video upload");
        System.out.println("Title: " + video.getTitle());
        System.out.println("Description: " + video.getDescription());

        if (savedVideo != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomMessage.builder()
                            .message("Video not uploaded!!!")
                            .success(false)
                            .build());
        }
    }

    // get all videos
    @GetMapping
    public List<Video> getAll() {
        return videoService.getAllVideos();
    }

    // stream video
    // http://localhost:8080/api/v1/videos/stream/285365
    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> stream(
            @PathVariable String videoId) {

        Video video = videoService.getVideo(videoId);

        String contentType = video.getContentType();

        String filepath = video.getFilePath();

        Resource resource = new FileSystemResource(filepath);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);

    }

    // stream video in chunks
    @GetMapping("/stream/range/{videoId}")
    public ResponseEntity<Resource> streamVideoRange(
            @PathVariable String videoId,
            @RequestHeader(value = "Range", required = false) String range) {
        System.out.println(range);

        Video video = videoService.getVideo(videoId);
        Path path = Paths.get(video.getFilePath());

        Resource resource = new FileSystemResource(path);

        String contentType = video.getContentType();

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // file length
        long fileLength = path.toFile().length();

        if (range == null) {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }

        // calculating start and end range
        long rangeStart;
        long rangeEnd;

        String[] ranges = range.replace("bytes=", "").split("-");
        rangeStart = Long.parseLong(ranges[0]);

        rangeEnd = rangeStart + AppConstants.CHUNK_SIZE - 1;

        if (rangeEnd >= fileLength) {
            rangeEnd = fileLength - 1;
        }

        // based on browser range of a video
        // if (range.length() > 1) {
        // rangeEnd = Long.parseLong(ranges[1]);
        // } else {
        // rangeEnd = fileLength - 1;
        // }

        // if (rangeEnd > fileLength - 1) {
        // rangeEnd = fileLength - 1;
        // }

        System.out.println("range start: " + rangeStart);
        System.out.println("range end: " + rangeEnd);
        InputStream inputStream;
        try {

            inputStream = Files.newInputStream(path);
            inputStream.skip(rangeStart);
            // inputStream.close();

            long contentLength = rangeEnd - rangeStart + 1;

            byte[] data = new byte[(int) contentLength];
            int read = inputStream.read(data, 0, data.length);
            System.out.println("Read number of bytes: " + read);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
            httpHeaders.add("Cache-control", "no-cache, no-store, must-revalidate");
            httpHeaders.add("Pragma", "no-cache");
            httpHeaders.add("Expires", "0");
            httpHeaders.add("X-Content-Type_Options", "nosniff");

            httpHeaders.setContentLength(contentLength);

            inputStream.close();
            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .headers(httpHeaders)
                    .contentType(MediaType.parseMediaType(contentType))
                    // .body(new InputStreamResource(inputStream));
                    .body(new ByteArrayResource(data));

        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

    // server hls playlist

    // master.m3u8 file
    @Value("${file.video.hsl}")
    private String HSL_DIR;

    @GetMapping("/{videoId}/master.m3u8")
    public ResponseEntity<Resource> serveMasterFile(
            @PathVariable String videoId) {

        // creating path
        Path path = Paths.get(HSL_DIR, videoId, "master.m3u8");
        System.out.println(path);

        if (!Files.exists(path)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resource resource = new FileSystemResource(path);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl")
                .body(resource);
    }

    // serve the segments

    @GetMapping("/{videoId}/{segment}.ts")
    public ResponseEntity<Resource> serveSegments(
            @PathVariable String videoId,
            @PathVariable String segment) {
        // create path for segment
        Path path = Paths.get(HSL_DIR, videoId, segment + ".ts");
        if (!Files.exists(path)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Resource resource = new FileSystemResource(path);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "video/mp2t")
                .body(resource);
    }
}
