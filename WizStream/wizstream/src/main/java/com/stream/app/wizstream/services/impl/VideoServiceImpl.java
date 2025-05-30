package com.stream.app.wizstream.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.app.wizstream.model.Video;
import com.stream.app.wizstream.repository.VideoRepository;
import com.stream.app.wizstream.services.VideoService;

import jakarta.annotation.PostConstruct;

@Service
public class VideoServiceImpl implements VideoService {

    @Value("${files.video}")
    String DIR;

    @Value("${file.video.hsl}")
    String HSL_DIR;

    private VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Video directory: " + DIR);
        File file = new File(DIR);

        try {

            Files.createDirectories(Paths.get(HSL_DIR));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Folder Created!!");
        } else {
            System.out.println("Folder already exists!!");
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {

        try {
            // original file name
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            // file path
            String cleanFileName = StringUtils.cleanPath(fileName);

            // folder path
            String cleanFolder = StringUtils.cleanPath(DIR);

            // create folder path with filename and content type
            Path path = Paths.get(cleanFolder, cleanFileName);

            System.out.println(contentType);
            System.out.println(path);

            // copy file to the folder path
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            // video meta data
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            Video savedVideo = videoRepository.save(video);
            // processing video
            processVideo(video.getVideoId());

            // delete actual video file and db entry if exception
            // TODO

            // save metadata to db
            return savedVideo;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        return video;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public String processVideo(String videoId) {

        Video video = this.getVideo(videoId);
        String filePath = video.getFilePath();

        // path where to store data
        Path videoPath = Paths.get(filePath);

        // String output360p = HSL_DIR + videoId + "/360p/";
        // String output720p = HSL_DIR + videoId + "/720p/";
        // String output1080p = HSL_DIR + videoId + "/1080p/";

        try {
            // Files.createDirectories(Paths.get(output360p));
            // Files.createDirectories(Paths.get(output720p));
            // Files.createDirectories(Paths.get(output1080p));

            // ffmpeg command
            Path outputPath = Paths.get(HSL_DIR, videoId);

            Files.createDirectories(outputPath);

            String ffmpegCmd = String.format(
                    "ffmpeg -i \"%s\" -c:v libx264 -c:a aac -strict -2 -f hls -hls_time 10 -hls_list_size 0 -hls_segment_filename \"%s/segment_%%3d.ts\"  \"%s/master.m3u8\" ",
                    videoPath, outputPath, outputPath);

            // StringBuilder ffmpegCmd = new StringBuilder();
            // ffmpegCmd.append("ffmpeg -i")
            // .append(videoPath.toString())
            // .append("")
            // .append(" -c:v libx264 -c:a aac")
            // .append(" ")
            // .append("-map 0:v -map 0:a -s:v:0 640x360 -b:v:0 800k ")
            // .append("-map 0:v -map 0:a -s:v:1 1280x720 -b:v:1 2800k ")
            // .append("-map 0:v -map 0:a -s:v:2 1920x1080 -b:v:2 5000k ")
            // .append("-var_stream_map \"v:0,a:0 v:1,a:0 v:2,a:0\" ")
            // .append("-master_pl_name
            // ").append(HSL_DIR).append(videoId).append("/master.m3u8 ")
            // .append("-f hls -hls_time 10 -hls_list_size 0 ")
            // .append("-hls_segment_filename \"").append(HSL_DIR).append(videoId)
            // .append("/v%v/fileSequence%d.ts\" ")
            // .append("\"").append(HSL_DIR).append(videoId).append("/v%v/prog_index.m3u8\"");

            System.out.println(ffmpegCmd);
            // file this command
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", ffmpegCmd);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exit = process.waitFor();
            if (exit != 0) {
                throw new RuntimeException("video processing failed!!");
            }

            return videoId;

        } catch (IOException e) {
            throw new RuntimeException("Video processing fail!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
