package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    private StudentService studentService;

    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        List<CourseEntity> courses = this.courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/{courseId}", produces = "application/json")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable String courseId) {
        CourseEntity course = this.courseService.getCourseById(Long.parseLong(courseId));
        if (course == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        CourseEntity createdCourse = this.courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{courseId}", produces = "application/json")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable String courseId, @RequestBody CourseEntity course) {
        course.setCourseId(Long.parseLong(courseId));
        CourseEntity updatedCourse = this.courseService.updateCourse(course);
        if (updatedCourse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @GetMapping(value = "/{courseId}/students", produces = "application/json")
    public ResponseEntity<Set<StudentEntity>> getStudents(@PathVariable("courseId") Long courseId) {
        Set<StudentEntity> students = studentService.getStudentsForCourse(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{courseId}", produces = "application/json")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
        try {
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
