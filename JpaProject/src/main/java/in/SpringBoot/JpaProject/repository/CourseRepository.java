package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Course save(Course course) {
        entityManager.persist(course);
        return course;
    }

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public List<Course> findAll() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    public Course update(Course course) {
        return entityManager.merge(course);
    }

    public void delete(Course course) {
        entityManager.remove(entityManager.contains(course) ? course : entityManager.merge(course));
    }
}
