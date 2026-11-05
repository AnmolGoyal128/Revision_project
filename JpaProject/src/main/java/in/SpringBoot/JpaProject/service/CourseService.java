package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.entity.Lesson;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        for (Lesson lesson : course.getLessons()) {
            lesson.setCourse(course);
        }

        if (course.getUserProfile() != null) {
            course.getUserProfile().setCourse(course);
        }

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
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course update(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id);
        if (existingCourse == null) {
            return null;
        }
        course.setId(id);
        prepareCourse(course);
        return courseRepository.update(course);
    }

    @Transactional
    public boolean delete(Long id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            return false;
        }
        courseRepository.delete(course);
        return true;
    }

    private void prepareCourse(Course course) {
        for (Lesson lesson : course.getLessons()) {
            lesson.setCourse(course);
        }
        if (course.getUserProfile() != null) {
            course.getUserProfile().setCourse(course);
        }
        Instructor instructor = course.getInstructor();
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor is required");
        }
        if (instructor.getId() != null) {
            Instructor existingInstructor = instructorRepository.findById(instructor.getId());
            if (existingInstructor == null) {
                throw new IllegalArgumentException("Instructor not found: " + instructor.getId());
            }
            course.setInstructor(existingInstructor);
        } else {
            instructorRepository.save(instructor);
        }
    }
}
