package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Lesson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class LessonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Lesson save(Lesson lesson) {
        entityManager.persist(lesson);
        return lesson;
    }
}
