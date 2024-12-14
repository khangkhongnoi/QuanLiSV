package com.studentservice.repository;

import com.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentResponsitory extends JpaRepository<Student, Long> {
}
