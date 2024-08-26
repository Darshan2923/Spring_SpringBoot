package com.quizappspring.quizspringapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizappspring.quizspringapp.models.Questions;
import com.quizappspring.quizspringapp.repo.QuestionsRepo;

@Service
public class QuestionService {

    private final QuestionsRepo questionsRepo;

    public QuestionService(QuestionsRepo questionRepo) {
        this.questionsRepo = questionRepo;
    }

    public List<Questions> getAllQuestions() {
        return questionsRepo.findAll();
    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionsRepo.findByCategory(category);
    }

    public String addQuestion(Questions questions) {

        questionsRepo.save(questions);
        return "Success";
    }

}
