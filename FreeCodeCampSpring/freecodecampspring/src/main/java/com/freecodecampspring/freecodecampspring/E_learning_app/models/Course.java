package com.freecodecampspring.freecodecampspring.E_learning_app.models;

import java.util.List;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Builder
@SuperBuilder
@Entity
public class Course extends BaseEntity {

        private String name;
        private String description;

        @ManyToMany
        @JoinTable(name = "authors_courses", joinColumns = {
                        @JoinColumn(name = "course_id")
        }, inverseJoinColumns = {
                        @JoinColumn(name = "author_id")
        })
        private List<Author> authors;

        @OneToMany(mappedBy = "course")
        private List<Section> sections;
}
