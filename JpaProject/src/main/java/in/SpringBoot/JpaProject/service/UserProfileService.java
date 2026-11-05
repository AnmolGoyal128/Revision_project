package in.SpringBoot.JpaProject.service;

import in.SpringBoot.JpaProject.entity.Course;
import in.SpringBoot.JpaProject.entity.UserProfile;
import in.SpringBoot.JpaProject.repository.CourseRepository;
import in.SpringBoot.JpaProject.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final CourseRepository courseRepository;

    public UserProfileService(UserProfileRepository userProfileRepository,
                              CourseRepository courseRepository) {
        this.userProfileRepository = userProfileRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public UserProfile save(UserProfile userProfile) {
        attachCourse(userProfile);
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    public UserProfile findById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Transactional
    public UserProfile update(Long id, UserProfile userProfile) {
        if (userProfileRepository.findById(id) == null) {
            return null;
        }
        userProfile.setId(id);
        attachCourse(userProfile);
        return userProfileRepository.update(userProfile);
    }

    @Transactional
    public boolean delete(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id);
        if (userProfile == null) {
            return false;
        }
        userProfileRepository.delete(userProfile);
        return true;
    }

    private void attachCourse(UserProfile userProfile) {
        if (userProfile.getCourse() == null || userProfile.getCourse().getId() == null) {
            throw new IllegalArgumentException("Course is required");
        }
        Course course = courseRepository.findById(userProfile.getCourse().getId());
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + userProfile.getCourse().getId());
        }
        userProfile.setCourse(course);
        course.setUserProfile(userProfile);
    }
}
