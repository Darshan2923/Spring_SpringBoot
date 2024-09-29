package com.freecodecampspring.freecodecampspring.E_learning_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freecodecampspring.freecodecampspring.E_learning_app.models.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
