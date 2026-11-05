package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    public Student update(Student student) {
        return entityManager.merge(student);
    }

    public void delete(Student student) {
        entityManager.remove(
                entityManager.contains(student) ? student : entityManager.merge(student)
        );
    }
}
