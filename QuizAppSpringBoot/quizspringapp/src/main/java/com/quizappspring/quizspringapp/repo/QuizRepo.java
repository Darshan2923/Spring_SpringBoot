package com.quizappspring.quizspringapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizappspring.quizspringapp.models.Quizzes;

public interface QuizRepo extends JpaRepository<Quizzes, Integer> {

}
