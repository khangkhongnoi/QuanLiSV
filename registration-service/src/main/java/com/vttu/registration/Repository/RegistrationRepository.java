package com.vttu.registration.Repository;

import com.vttu.registration.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    List<Registration> findByStudentId(String studentId);
}
