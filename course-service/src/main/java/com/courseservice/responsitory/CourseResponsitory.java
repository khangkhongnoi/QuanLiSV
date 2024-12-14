package com.courseservice.responsitory;

import com.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseResponsitory extends JpaRepository<Course, Integer> {
}
