package in.SpringBoot.JpaProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(
            name = "instructor_id",
            nullable = false
    )
    private Instructor instructor;

    @OneToOne
    private UserProfile userProfile;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

//    @ManyToMany
//    private Set<Student> student;


}
