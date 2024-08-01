package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{studentId}", produces = "application/json")
    public StudentEntity getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(Long.parseLong(studentId));
    }

    @PostMapping(produces = "application/json")
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return studentService.createStudent(student);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity student) {
        try {
            StudentEntity updatedStudent = studentService.updateStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{studentId}", produces = "application/json")
    public void deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(Long.parseLong(studentId));
    }

    @GetMapping(value = "/{id}/courses", produces = "application/json")
    public Set<CourseEntity> getCourses(@PathVariable String id) {
        return studentService.getCoursesForStudent(Long.parseLong(id));
    }

    @PatchMapping(value = "/{studentId}/courses/{courseId}", produces = "application/json")
    public ResponseEntity<StudentEntity> assignCourseToStudent(@PathVariable Long studentId,
            @PathVariable Long courseId) {
        try {
            StudentEntity student = studentService.assignCourseToStudent(studentId, courseId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
