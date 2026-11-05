package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.entity.Student;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Student save(Student student) {
        List<Course> courseReferences = new ArrayList<>(student.getCourses());
        student.setCourses(new ArrayList<>());
        Student savedStudent = studentRepository.save(student);
        attachCourses(savedStudent, courseReferences);
        return savedStudent;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public Student update(Long id, Student student) {
        if (studentRepository.findById(id) == null) {
            return null;
        }
        student.setId(id);
        List<Course> courseReferences = new ArrayList<>(student.getCourses());
        student.setCourses(new ArrayList<>());
        Student updatedStudent = studentRepository.update(student);
        attachCourses(updatedStudent, courseReferences);
        return updatedStudent;
    }

    @Transactional
    public boolean delete(Long id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return false;
        }
        for (Course course : List.copyOf(student.getCourses())) {
            course.removeStudent(student);
        }
        studentRepository.delete(student);
        return true;
    }

    @Transactional
    public boolean addCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        if (student == null || course == null) {
            return false;
        }
        student.addCourse(course);
        return true;
    }

    @Transactional
    public boolean removeCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId);
        Course course = courseRepository.findById(courseId);
        if (student == null || course == null) {
            return false;
        }
        student.removeCourse(course);
        return true;
    }

    private void attachCourses(Student student, List<Course> courseReferences) {
        for (Course courseReference : courseReferences) {
            if (courseReference.getId() == null) {
                throw new IllegalArgumentException("Existing course id is required");
            }
            Course course = courseRepository.findById(courseReference.getId());
            if (course == null) {
                throw new IllegalArgumentException("Course not found: " + courseReference.getId());
            }
            student.addCourse(course);
        }
    }
}
