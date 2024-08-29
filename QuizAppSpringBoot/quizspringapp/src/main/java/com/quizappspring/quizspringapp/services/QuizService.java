package com.quizappspring.quizspringapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

import com.quizappspring.quizspringapp.controllers.QuestionWrapper;
import com.quizappspring.quizspringapp.controllers.Responses;
// import com.quizappspring.quizspringapp.controllers.Responses;
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
            // Fetch all questions and then limit to the required number
            List<Questions> questions = questionsRepo.findRandomQuestionsByCategory(category)
                    .stream()
                    .limit(numQ)
                    .collect(Collectors.toList());

            // Create a new quiz and set its properties
            Quizzes quiz = new Quizzes();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            // Save the quiz to the repository
            quizRepo.save(quiz);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    public List<QuestionWrapper> getQuizQuestions(Integer quizId) {
        Optional<Quizzes> quiz = quizRepo.findById(quizId);
        List<Questions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for (Questions q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestiontitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionsForUsers.add(qw);
        }
        return questionsForUsers;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> responses) {
        Quizzes quiz = quizRepo.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Responses response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightanswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
