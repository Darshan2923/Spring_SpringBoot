package com.freecodecampspring.freecodecampspring.E_learning_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freecodecampspring.freecodecampspring.E_learning_app.models.Video;

public interface VideoRepo extends JpaRepository<Video, Integer> {

}
