package com.vttu.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StudentService studentService;

    @KafkaListener(topics = "registration-events", groupId = "notification-group")
    public void consumeMessage(String message) {
        System.out.println("Received Message: " +message);

        // Giả sử message có dạng: "Student 1 registered courses [1, 2] successfully."
        String studentId = extractStudentIdFromMessage(message);

        // Lấy email từ Student-Service
        String email = studentService.getStudentEmail(studentId);

        // Extract details (customize as per your message format)

        String subject = "Thông Báo VTTU";
        String body = "Bạn đã đăng ký thành công khóa học.Xin cảm ơn!";

        // Gửi email
        emailService.sendEmail(email, subject, body);
    }

    private String extractStudentIdFromMessage(String message) {
        // Tách studentId từ message, tùy thuộc vào format của message
        return message.split(" ")[1]; // Lấy "1" từ "Student 1 registered courses..."
    }
}
