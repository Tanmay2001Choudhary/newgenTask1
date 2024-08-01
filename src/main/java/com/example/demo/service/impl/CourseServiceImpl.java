package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CourseEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    };

    @Override
    public CourseEntity getCourseById(Long courseId) {
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent())
            return course.get();
        else
            throw new RuntimeException("CourseEntity not found");
    }

    @Override
    public CourseEntity createCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    @Override
    public CourseEntity updateCourse(CourseEntity course) {
        if (!courseRepository.existsById(course.getCourseId())) {
            throw new RuntimeException("CourseEntity not found");
        }
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("CourseEntity not found");
        }
        courseRepository.deleteById(courseId);
    }
}
