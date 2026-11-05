package in.SpringBoot.JpaProject.controller;

import in.SpringBoot.JpaProject.entity.Lesson;
import in.SpringBoot.JpaProject.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.save(lesson));
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Long id) {
        Lesson lesson = lessonService.findById(id);
        return lesson == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(lesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id,
                                               @RequestBody Lesson lesson) {
        Lesson updatedLesson = lessonService.update(id, lesson);
        return updatedLesson == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        return lessonService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
