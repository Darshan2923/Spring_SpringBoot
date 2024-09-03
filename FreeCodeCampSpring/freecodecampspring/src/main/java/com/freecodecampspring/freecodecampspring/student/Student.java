package com.freecodecampspring.freecodecampspring.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.freecodecampspring.freecodecampspring.school.School;
import com.freecodecampspring.freecodecampspring.student_profile.StudentProfile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "c_fname", length = 20)
    private String firstName;

    private String lastName;

    @Column(unique = true, updatable = false)
    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    private int age;
}
