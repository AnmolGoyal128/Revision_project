package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
