package com.vttu.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class StudentService {

    @Value("${api_file_service}")
    private String studentServiceUrl; // URL của Student-Service

    private final RestTemplate restTemplate;

    @Autowired
    public StudentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getStudentEmail(String studentId) {
        String url = String.format("%s/student-service/student/%s", studentServiceUrl, studentId);

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> studentData = response.getBody();
                return studentData != null ? (String) studentData.get("email") : null;
            }
        } catch (RestClientException ex) {
            System.err.println("Error fetching student email: " + ex.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy email
    }
}
