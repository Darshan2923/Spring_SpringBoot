package com.quizappspring.quizspringapp.services;

import java.util.ArrayList;
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
        try {
            return questionsRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list in case of an error
    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionsRepo.findByCategory(category);
    }

    public String addQuestion(Questions questions) {
        try {
            questionsRepo.save(questions);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }
}
