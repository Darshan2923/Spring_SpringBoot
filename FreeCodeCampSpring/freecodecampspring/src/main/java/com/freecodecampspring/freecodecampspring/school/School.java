package com.freecodecampspring.freecodecampspring.school;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.freecodecampspring.freecodecampspring.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "school")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;

    private String name;

    // Constructor accepting only the school name
    public School(String name) {
        this.name = name;
    }

}
