package com.quizappspring.quizspringapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizappspring.quizspringapp.models.Questions;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questiondb q WHERE q.category = :category ORDER BY RANDOM()", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(@Param("category") String category);
}
