package com.courseservice.services;

import com.courseservice.entity.Course;
import com.courseservice.responsitory.CourseResponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {

    CourseResponsitory responsitory;

    public boolean saveCourse(Course course) {

       try {
           responsitory.save(course);
           return true;
       }catch (Exception e) {
           return false;
       }
    }

    public List<Course> getCourses() {
        return responsitory.findAll();
    }
}
