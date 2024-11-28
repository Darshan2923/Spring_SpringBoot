package com.stream.app.wizstream.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stream.app.wizstream.model.Video;

public interface VideoService {

    // save video
    Video save(Video video, MultipartFile file);

    // get video by id
    Video getVideo(String videoId);

    // get video by title
    Video getByTitle(String title);

    List<Video> getAllVideos();

    // video processing
    String processVideo(String videoId);
}
