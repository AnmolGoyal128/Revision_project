package in.SpringBoot.JpaProject.controller;

import in.SpringBoot.JpaProject.entity.UserProfile;
import in.SpringBoot.JpaProject.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<UserProfile> create(@RequestBody UserProfile userProfile) {
        return ResponseEntity.ok(userProfileService.save(userProfile));
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> findAll() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> findById(@PathVariable Long id) {
        UserProfile profile = userProfileService.findById(id);
        return profile == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Long id,
                                              @RequestBody UserProfile userProfile) {
        UserProfile updated = userProfileService.update(id, userProfile);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userProfileService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
