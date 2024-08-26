package com.quizappspring.quizspringapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizappspring.quizspringapp.models.Questions;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);
}
