package com.vttu.registration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registrations") // Tên collection trong MongoDB
@Data
public class Registration {
    @Id
    private String id; // Mã đăng ký (do MongoDB tự tạo)
    private String studentId; // ID của sinh viên
    private String courseId;  // ID của khóa học
    private String registrationDate; // Ngày đăng ký
}
