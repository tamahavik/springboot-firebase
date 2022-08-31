package com.example.springbootfirebase.service.impl;

import com.example.springbootfirebase.domain.Student;
import com.example.springbootfirebase.enums.StudentStatus;
import com.example.springbootfirebase.repository.StudentRepository;
import com.example.springbootfirebase.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        Student studentWithId = student.generateUUID();
        return studentRepository.save(studentWithId);
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data Not Found"));
    }

    @Override
    public Student updateStudent(final Student student) {
        Student dbStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Bad Request"));

        Student updated = dbStudent.toBuilder()
                .nis(student.getNis())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .status(student.getStatus())
                .email(student.getEmail())
                .build();

        return studentRepository.save(updated);
    }

    @Override
    public String deleteStudent(String id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bad Request"));

        return studentRepository.delete(dbStudent.getId());
    }

    @Override
    public List<Student> getStudentStatusActive() {
        return studentRepository.findByField(Student.STATUS, StudentStatus.ACTIVE.name());
    }
}
