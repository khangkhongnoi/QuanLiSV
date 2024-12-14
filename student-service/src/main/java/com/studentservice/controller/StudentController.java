package com.studentservice.controller;


import com.studentservice.entity.Student;
import com.studentservice.service.StdudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StdudentService stdudentService;

    @PostMapping
    boolean saveStudent(@RequestBody Student student) {
        try {
            stdudentService.save(student);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    List<Student> getStudents() {
        return stdudentService.findAll();
    }

    @GetMapping("/{id}")
    Optional<Student> getStudents(@PathVariable long id) {
        return stdudentService.findById(id);
    }

    @PutMapping("/{id}")
    boolean updateStudent(@PathVariable long id, @RequestBody Student student) {
        try {
            stdudentService.update(id, student);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    boolean deleteStudent(@PathVariable long id){
        try {
            stdudentService.delete(id);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
