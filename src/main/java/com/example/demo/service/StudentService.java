package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentEntity;

public interface StudentService {
    List<StudentEntity> getAllStudents();

    StudentEntity getStudentById(Long id);

    StudentEntity createStudent(StudentEntity student);

    StudentEntity updateStudent(StudentEntity student);

    void deleteStudent(Long id);

    Set<CourseEntity> getCoursesForStudent(Long studentId);

    Set<StudentEntity> getStudentsForCourse(Long studentId);

    StudentEntity assignCourseToStudent(Long studentId, Long courseId);

}
