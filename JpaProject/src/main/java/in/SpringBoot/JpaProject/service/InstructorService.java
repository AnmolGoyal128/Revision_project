package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
