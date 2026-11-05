package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Lesson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Lesson save(Lesson lesson) {
        entityManager.persist(lesson);
        return lesson;
    }

    public Lesson findById(Long id) {
        return entityManager.find(Lesson.class, id);
    }

    public List<Lesson> findAll() {
        return entityManager.createQuery("SELECT l FROM Lesson l", Lesson.class)
                .getResultList();
    }

    public Lesson update(Lesson lesson) {
        return entityManager.merge(lesson);
    }

    public void delete(Lesson lesson) {
        entityManager.remove(
                entityManager.contains(lesson) ? lesson : entityManager.merge(lesson)
        );
    }
}
