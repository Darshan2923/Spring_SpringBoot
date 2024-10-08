package com.quizappspring.quizspringapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizappspring.quizspringapp.models.Questions;
import com.quizappspring.quizspringapp.services.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        List<Questions> questions = questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions) {
        String response = questionService.addQuestion(questions);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
