package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public void createInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id);
    }

    @Transactional
    public Instructor update(Long id, Instructor instructor) {
        if (instructorRepository.findById(id) == null) {
            return null;
        }
        instructor.setId(id);
        return instructorRepository.update(instructor);
    }

    @Transactional
    public boolean delete(Long id) {
        Instructor instructor = instructorRepository.findById(id);
        if (instructor == null) {
            return false;
        }
        instructorRepository.delete(instructor);
        return true;
    }
}
