package com.stream.app.wizstream.model;

// import java.util.ArrayList;
// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

@Entity
@Data
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    private String course_id;
    private String course_title;

    // If you want to create course playlist, this is the way
    // @OneToMany(mappedBy = "course")
    // private List<Video> playlist = new ArrayList<>();
}
