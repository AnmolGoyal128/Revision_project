package in.SpringBoot.JpaProject.controller;

import in.SpringBoot.JpaProject.entity.Instructor;
import in.SpringBoot.JpaProject.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Instructor>> getInstructors() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        return instructor == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id,
                                                       @RequestBody Instructor instructor) {
        Instructor updatedInstructor = instructorService.update(id, instructor);
        return updatedInstructor == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        return instructorService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
