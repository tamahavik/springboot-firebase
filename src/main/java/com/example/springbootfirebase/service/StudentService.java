package com.example.springbootfirebase.service;

import com.example.springbootfirebase.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    Student saveStudent(Student student);

    Student getStudentById(String id);

    Student updateStudent(Student student);

    String deleteStudent(String id);

    List<Student> getStudentStatusActive();

}
