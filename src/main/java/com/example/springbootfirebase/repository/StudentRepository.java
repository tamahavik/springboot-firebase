package com.example.springbootfirebase.repository;

import com.example.springbootfirebase.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> findAll();

    Student save(Student student);

    Optional<Student> findById(String id);

    String delete(String id);

    List<Student> findByField(String field, String value);

}
