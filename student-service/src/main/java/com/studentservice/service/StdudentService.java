package com.studentservice.service;

import com.studentservice.entity.Student;
import com.studentservice.repository.StudentResponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StdudentService {


    StudentResponsitory studentResponsitoryp;
    private final StudentResponsitory studentResponsitory;

    public boolean save(Student student) {

       try {
           studentResponsitory.save(student);

           return true;
       } catch (Exception e) {

           return false;

       }
    }

    public List<Student> findAll() {
        return studentResponsitoryp.findAll();
    }

    public Optional<Student> findById(long id) {
        return studentResponsitoryp.findById(id);
    }

    public boolean update(long id, Student request) {
        try {

            Student student = studentResponsitoryp.findById(id)
                    .orElseThrow(() -> new Exception("Student not found"));

            student.setHoten(request.getHoten());
            student.setGioitinh(request.getGioitinh());
            student.setNgaysinh(request.getNgaysinh());
            student.setDiachi(request.getDiachi());
            student.setMssv(request.getMssv());
            student.setKhoa(request.getKhoa());
            student.setSdt(request.getSdt());
            student.setEmail(request.getEmail());
            studentResponsitoryp.save(student);

            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public boolean delete(long id) {
       try{
           studentResponsitoryp.deleteById(id);
           return true;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }
}
