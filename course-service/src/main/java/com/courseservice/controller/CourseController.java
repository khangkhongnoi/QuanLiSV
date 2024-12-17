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

    @PutMapping("/{ma_course}")
    boolean updateCourse(@PathVariable int ma_course, @RequestBody Course course) {
        try {
            courseService.updateCourse(course,ma_course);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{ma_course}")
    boolean deleteCourse(@PathVariable int ma_course) {
        try {
            courseService.deleteCourse(ma_course);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
