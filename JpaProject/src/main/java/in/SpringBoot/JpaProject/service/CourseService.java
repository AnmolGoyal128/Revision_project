package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public Course save(Course course) {
        Instructor instructor = course.getInstructor();

        if (instructor == null) {
            throw new IllegalArgumentException("Instructor is required");
        }

        if (instructor.getId() == null) {
            instructorRepository.save(instructor);
        } else {
            Instructor existingInstructor = instructorRepository.findById(instructor.getId());
            if (existingInstructor == null) {
                throw new IllegalArgumentException(
                        "Instructor not found: " + instructor.getId()
                );
            }
            course.setInstructor(existingInstructor);
        }

//        Course course1 = new Course();
//        course1.setName("Java Programming");
//        course1.setDescription("Learn the fundamentals of Java programming.");
//        course1.setInstructor(instructor1);
//
        Instructor instructor1 = new Instructor();
        instructor1.setName("Anmol");
        instructor1.setEmail("anmol@gmail.com");
        instructor1.setBio("Anmol is a skilled instructor with expertise in various subjects. With a passion for teaching and a commitment to student success, Anmol creates engaging learning experiences that inspire and empower learners to reach their full potential.");
        instructorRepository.save(instructor1);

        course.setInstructor(instructor1);

        return courseRepository.save(course);
    }
}
