package com.studentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mastudent;

    String hoten;

    int gioitinh;

    String ngaysinh;

    String diachi;

    String mssv;

    int khoa;

    String sdt;

    String email;

}
