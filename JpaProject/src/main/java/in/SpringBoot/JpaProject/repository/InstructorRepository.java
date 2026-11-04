package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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

}
