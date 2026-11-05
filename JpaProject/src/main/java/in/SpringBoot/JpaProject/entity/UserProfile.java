package in.SpringBoot.JpaProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagetAudience;
    private String prerequisites;
    private String SyllabusOverview;


    @OneToOne(mappedBy = "userProfile")
    @JsonBackReference("course-user-profile")
    private Course course;


}
