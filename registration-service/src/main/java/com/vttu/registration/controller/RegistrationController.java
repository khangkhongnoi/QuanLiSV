package com.vttu.registration.controller;

import com.vttu.registration.entity.CourseRegistrationDTO;
import com.vttu.registration.entity.Registration;
import com.vttu.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register/{studentId}")
    public ResponseEntity<?> registerCourses( @PathVariable int studentId,
            @RequestBody List<Integer> courseIds) {  // Nhận một mảng các courseId (dạng số)

        // Gọi service để xử lý đăng ký cho từng khóa học
        List<Registration> registeredCourses = registrationService.registerCourses(courseIds,studentId);

        // Trả về danh sách kết quả đã đăng ký
        return ResponseEntity.ok(registeredCourses);
    }

    // Endpoint để lấy danh sách khóa học của sinh viên
    @GetMapping("/courses/{studentId}")
    public ResponseEntity<List<CourseRegistrationDTO>> getCoursesByStudentId(@PathVariable String studentId) {
        // Gọi service để lấy danh sách khóa học
        List<CourseRegistrationDTO> courses = registrationService.getCoursesByStudentId(studentId);

        // Trả về danh sách khóa học dưới dạng ResponseEntity
        return ResponseEntity.ok(courses);
    }
}
