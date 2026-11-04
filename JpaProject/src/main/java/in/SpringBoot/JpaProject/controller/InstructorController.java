package in.SpringBoot.JpaProject.controller;

import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.service.InstructorService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<String> createInstructor(@RequestBody Instructor instructor) {
        instructorService.createInstructor(instructor);
        return ResponseEntity.ok("Instructor created successfully");
    }
}
