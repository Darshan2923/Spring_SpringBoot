package com.quizappspring.quizspringapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quizappspring.quizspringapp.models.Questions;
import com.quizappspring.quizspringapp.models.Quizzes;
import com.quizappspring.quizspringapp.repo.QuestionsRepo;
import com.quizappspring.quizspringapp.repo.QuizRepo;

@Service
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuestionsRepo questionsRepo;

    public QuizService(QuizRepo quizRepo, QuestionsRepo questionsRepo) {
        this.quizRepo = quizRepo;
        this.questionsRepo = questionsRepo;
    }

    public String createQuiz(String category, int numQ, String title) {
        try {
            List<Questions> questions = questionsRepo.findRandomQuestionsByCategory(category);
            Quizzes quiz = new Quizzes();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            questions.stream().limit(numQ).collect(Collectors.toList());
            quizRepo.save(quiz);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
