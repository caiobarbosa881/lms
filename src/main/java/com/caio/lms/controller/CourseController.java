package com.caio.lms.controller;

import com.caio.lms.domain.course.Course;
import com.caio.lms.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Criar curso
    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Course savedCourse = courseService.create(course);
        return ResponseEntity.ok(savedCourse);
    }

    // Listar todos os cursos
    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }
}