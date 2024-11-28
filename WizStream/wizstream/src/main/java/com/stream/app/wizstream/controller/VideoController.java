package com.stream.app.wizstream.controller;

import java.util.List;
import java.util.UUID;

// import org.apache.catalina.connector.Response;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.stream.app.wizstream.model.Video;
import com.stream.app.wizstream.services.VideoService;
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
}
