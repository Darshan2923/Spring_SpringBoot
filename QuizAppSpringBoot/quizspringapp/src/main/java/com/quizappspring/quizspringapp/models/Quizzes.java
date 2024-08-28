package com.quizappspring.quizspringapp.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quizzes {

    @Id
    private Integer id;
    private String title;
    @ManyToMany
    private List<Questions> questions;
}
