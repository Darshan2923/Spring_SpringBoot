package com.quizappspring.quizspringapp.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Responses {
    private Integer id;
    private String response;
}