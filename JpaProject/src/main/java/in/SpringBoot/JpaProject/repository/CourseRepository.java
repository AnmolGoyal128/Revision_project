package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
@PersistenceContext
public class CourseRepository {
    private EntityManager entityManager;

    public Course save(Course course) {
        entityManager.persist(course);
        return course;
    }
}
