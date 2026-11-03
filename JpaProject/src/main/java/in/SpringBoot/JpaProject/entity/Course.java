package in.SpringBoot.JpaProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CourseName;
    private int NoOfStudents;
    private int NoOfLesson;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    @ManyToMany
    private List<Student> students;
}
