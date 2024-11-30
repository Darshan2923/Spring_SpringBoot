package com.syncode.syncode.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.syncode.syncode.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    Optional<Project> findByRoom(String room);
}
