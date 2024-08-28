package com.quizappspring.quizspringapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizappspring.quizspringapp.services.QuizService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    private QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,
            @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
    }

}
