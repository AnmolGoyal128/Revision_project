package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Lesson;
import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Lesson save(Lesson lesson) {
        if (lesson.getCourse() == null || lesson.getCourse().getId() == null) {
            throw new IllegalArgumentException("Course is required");
        }

        Course course = courseRepository.findById(lesson.getCourse().getId());
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + lesson.getCourse().getId());
        }

        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }
}
