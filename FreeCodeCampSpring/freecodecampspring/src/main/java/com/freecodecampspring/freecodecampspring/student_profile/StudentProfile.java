package com.freecodecampspring.freecodecampspring.student_profile;

import com.freecodecampspring.freecodecampspring.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studentprofile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String bio;

}
