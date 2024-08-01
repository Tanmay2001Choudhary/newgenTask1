package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CourseEntity;

public interface CourseService {
    List<CourseEntity> getAllCourses();

    CourseEntity getCourseById(Long id);

    CourseEntity createCourse(CourseEntity course);

    CourseEntity updateCourse(CourseEntity course);

    void deleteCourse(Long id);
}
