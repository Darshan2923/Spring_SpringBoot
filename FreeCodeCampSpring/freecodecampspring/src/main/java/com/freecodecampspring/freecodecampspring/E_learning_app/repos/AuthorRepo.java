package com.freecodecampspring.freecodecampspring.E_learning_app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freecodecampspring.freecodecampspring.E_learning_app.models.Author;

import jakarta.transaction.Transactional;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

    @Modifying
    @Transactional
    @Query("update Author a set a.age=:age where a.id=:id")
    int updateAuthor(int age, int id);

    List<Author> findByFirstName(String firstName);

    List<Author> findByFirstNameIgnoreCase(String firstName);

    List<Author> findByFirstNameStartsWithIgnoreCase(String firstName);

    List<Author> findByNamedQuery(@Param("age") int age);

}
