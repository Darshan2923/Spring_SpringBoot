package com.freecodecampspring.freecodecampspring.E_learning_app.models;

// import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
// import jakarta.persistence.SequenceGenerator;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;
// import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name = "e_author")
@Entity
@NamedQuery(name = "Author.findByNamedQuery", query = "select a from Author a where a.age>=:age")
@AllArgsConstructor
// @Builder
@SuperBuilder
public class Author extends BaseEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "author_sequence")
    // @SequenceGenerator(name = "author_sequence", sequenceName =
    // "author_sequence", allocationSize = 1)
    // private Integer id;
    @Column(name = "f_name", length = 35)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private int age;
    // @Column(updatable = false, nullable = false)
    // private LocalDateTime createdAt;
    // @Column(insertable = false)
    // private LocalDateTime lastModified;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;

    // @PrePersist
    // protected void onCreate() {
    // this.createdAt = LocalDateTime.now();
    // this.lastModified = LocalDateTime.now(); // Set lastModified on insert
    // }

    // @PreUpdate
    // protected void onUpdate() {
    // this.lastModified = LocalDateTime.now(); // Update lastModified on update
    // }
}
