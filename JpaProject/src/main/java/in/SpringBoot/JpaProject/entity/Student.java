package in.SpringBoot.JpaProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Gender gender;

    @ManyToMany(mappedBy = "students")
    @JsonBackReference("course-students")
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
        if (!course.getStudents().contains(this)) {
            course.getStudents().add(this);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }
}
