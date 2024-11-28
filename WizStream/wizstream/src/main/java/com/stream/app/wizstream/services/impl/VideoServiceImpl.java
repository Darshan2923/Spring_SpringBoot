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
    String dir;

    private VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Video directory: " + dir);
        File file = new File(dir);

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
            String cleanFolder = StringUtils.cleanPath(dir);

            // create folder path with filename and content type
            Path path = Paths.get(cleanFolder, cleanFileName);

            System.out.println(contentType);
            System.out.println(path);

            // copy file to the folder path
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            // video meta data
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            // save metadata to db
            return videoRepository.save(video);

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByTitle'");
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

}
