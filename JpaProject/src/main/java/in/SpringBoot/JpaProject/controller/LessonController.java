package in.SpringBoot.JpaProject.controller;

import in.SpringBoot.JpaProject.entity.Lesson;
import in.SpringBoot.JpaProject.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson lesson1 = lessonService.save(lesson);
        return ResponseEntity.ok(lesson1);
    }
}
