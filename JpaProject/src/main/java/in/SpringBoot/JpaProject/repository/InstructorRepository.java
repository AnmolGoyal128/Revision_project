package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Instructor save(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor;
    }

    public Instructor findById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        return entityManager.createQuery("SELECT i FROM Instructor i", Instructor.class)
                .getResultList();
    }

    public Instructor update(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    public void delete(Instructor instructor) {
        entityManager.remove(
                entityManager.contains(instructor) ? instructor : entityManager.merge(instructor)
        );
    }

}
