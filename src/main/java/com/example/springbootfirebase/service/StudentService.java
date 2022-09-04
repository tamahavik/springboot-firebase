package com.example.springbootfirebase.service;

import com.example.springbootfirebase.dto.StudentRequest;
import com.example.springbootfirebase.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudent();

    StudentResponse saveStudent(StudentRequest student);

    StudentResponse getStudentById(String id);

    StudentResponse updateStudent(StudentRequest student);

    String deleteStudent(String id);

    List<StudentResponse> getStudentStatusActive();

}
