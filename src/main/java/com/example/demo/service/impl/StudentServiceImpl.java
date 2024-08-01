package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentEntity updateStudent(StudentEntity student) {
        StudentEntity existingStudent = studentRepository.findById(student.getRollno())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setName(student.getName());
        existingStudent.setCourses(student.getCourses());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Set<StudentEntity> getStudentsForCourse(Long courseId) {
        return courseRepository.findById(courseId).map(CourseEntity::getStudents)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Set<CourseEntity> getCoursesForStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .map(StudentEntity::getCourses)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public StudentEntity assignCourseToStudent(Long studentId, Long courseId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        return studentRepository.save(student);
    }
}
