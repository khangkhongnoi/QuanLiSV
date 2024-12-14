package com.courseservice.controller;

import com.courseservice.entity.Course;
import com.courseservice.services.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {
    CourseService courseService;

    @PostMapping
    boolean saveCourse(@RequestBody Course course) {
        try {
            courseService.saveCourse(course);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    List<Course> getCourses() {
        try {
            return courseService.getCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
