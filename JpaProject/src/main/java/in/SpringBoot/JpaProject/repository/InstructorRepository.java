package in.SpringBoot.JpaProject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;


}
