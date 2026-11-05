package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Lesson;
import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        attachCourse(lesson);
        return lessonRepository.save(lesson);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Transactional
    public Lesson update(Long id, Lesson lesson) {
        if (lessonRepository.findById(id) == null) {
            return null;
        }
        lesson.setId(id);
        attachCourse(lesson);
        return lessonRepository.update(lesson);
    }

    @Transactional
    public boolean delete(Long id) {
        Lesson lesson = lessonRepository.findById(id);
        if (lesson == null) {
            return false;
        }
        lessonRepository.delete(lesson);
        return true;
    }

    private void attachCourse(Lesson lesson) {
        if (lesson.getCourse() == null || lesson.getCourse().getId() == null) {
            throw new IllegalArgumentException("Course is required");
        }

        Course course = courseRepository.findById(lesson.getCourse().getId());
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + lesson.getCourse().getId());
        }

        lesson.setCourse(course);
    }
}
