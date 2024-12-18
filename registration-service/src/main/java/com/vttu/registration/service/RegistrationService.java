package com.vttu.registration.service;

import com.vttu.registration.Repository.RegistrationRepository;
import com.vttu.registration.entity.CourseRegistrationDTO;
import com.vttu.registration.entity.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    // Logic để đăng ký danh sách khóa học
    public List<Registration> registerCourses(List<Integer> newCourseIds,int studentId) {
//        List<Registration> registrations = new ArrayList<>();
//
//        // Giả sử bạn cần lấy thông tin sinh viên đã đăng ký các khóa học này
//        for (Integer courseId : courseIds) {
//            Registration registration = new Registration();
//            registration.setStudentId(String.valueOf(studentId));
//            registration.setCourseId(courseId.toString());  // Chuyển courseId thành String nếu cần
//            registration.setRegistrationDate(LocalDateTime.now().toString());
//
//            // Lưu vào MongoDB hoặc cơ sở dữ liệu
//            registrations.add(registrationRepository.save(registration));
//        }
//
//        return registrations; // Trả về danh sách các đăng ký đã được xử lý
        // Lấy danh sách các khóa học đã đăng ký trước đó
        List<Registration> existingRegistrations = registrationRepository.findByStudentId(String.valueOf(studentId));

        // Chuyển danh sách các courseId đã đăng ký thành một tập hợp để dễ dàng so sánh
        Set<String> existingCourseIds = existingRegistrations.stream()
                .map(Registration::getCourseId)
                .collect(Collectors.toSet());

        // Danh sách mới để lưu các đăng ký sau khi cập nhật
        List<Registration> updatedRegistrations = new ArrayList<>();

        // Thêm các khóa học mới (chưa có trong danh sách đã đăng ký)
        for (Integer courseId : newCourseIds) {
            String courseIdStr = courseId.toString();
            if (!existingCourseIds.contains(courseIdStr)) {
                Registration registration = new Registration();
                registration.setStudentId(String.valueOf(studentId));
                registration.setCourseId(courseIdStr);
                registration.setRegistrationDate(LocalDateTime.now().toString());
                updatedRegistrations.add(registration);
            }
        }

        // Xóa các khóa học không còn được chọn (có trong danh sách đăng ký cũ nhưng không còn trong danh sách mới)
        List<Registration> coursesToRemove = existingRegistrations.stream()
                .filter(registration -> !newCourseIds.contains(Integer.parseInt(registration.getCourseId())))
                .collect(Collectors.toList());

        // Xóa các khóa học không còn trong danh sách mới
        registrationRepository.deleteAll(coursesToRemove);

        // Lưu các đăng ký mới
        List<Registration> savedRegistrations = registrationRepository.saveAll(updatedRegistrations);

        // Kết hợp cả các đăng ký cũ và mới
        existingRegistrations.addAll(savedRegistrations);

        // Gửi thông báo đến Kafka
        String message = String.format("Student %d registered courses successfully.",
                studentId);

        kafkaTemplate.send("registration-events", message);

        return existingRegistrations;
    }

    public List<CourseRegistrationDTO> getCoursesByStudentId(String studentId) {
        // Lấy danh sách đăng ký của sinh viên
        List<Registration> registrations = registrationRepository.findByStudentId(studentId);

        // Chuyển các đăng ký thành danh sách DTO với thông tin studentId và courseId
        return registrations.stream()
                .map(registration -> new CourseRegistrationDTO(
                        registration.getStudentId(),
                        registration.getCourseId()))
                .collect(Collectors.toList());
    }
}

