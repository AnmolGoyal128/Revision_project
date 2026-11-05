package in.SpringBoot.JpaProject.repository;

import in.SpringBoot.JpaProject.entity.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UserProfile save(UserProfile userProfile) {
        entityManager.persist(userProfile);
        return userProfile;
    }

    public UserProfile findById(Long id) {
        return entityManager.find(UserProfile.class, id);
    }

    public List<UserProfile> findAll() {
        return entityManager.createQuery("SELECT p FROM UserProfile p", UserProfile.class)
                .getResultList();
    }

    public UserProfile update(UserProfile userProfile) {
        return entityManager.merge(userProfile);
    }

    public void delete(UserProfile userProfile) {
        entityManager.remove(
                entityManager.contains(userProfile) ? userProfile : entityManager.merge(userProfile)
        );
    }
}
