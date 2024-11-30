package com.syncode.syncode.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syncode.syncode.exception.GeneralException;
import com.syncode.syncode.model.Project;

@Component
public class ProjectConverter {

    public String convertToJsonString(Project project) {
        try {
            return new ObjectMapper().writeValueAsString(project);
        } catch (JsonProcessingException e) {
            throw new GeneralException("json deserialization error", HttpStatus.BAD_REQUEST);
        }
    }

    public String convertToJsonString(List<Project> projectList) {
        try {
            return new ObjectMapper().writeValueAsString(projectList);
        } catch (Exception e) {
            throw new GeneralException("json deserialization error", HttpStatus.BAD_REQUEST);
        }
    }

}
