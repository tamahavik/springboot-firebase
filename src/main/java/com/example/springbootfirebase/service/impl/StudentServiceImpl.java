package com.example.springbootfirebase.service.impl;

import com.example.springbootfirebase.domain.Student;
import com.example.springbootfirebase.dto.StudentRequest;
import com.example.springbootfirebase.dto.StudentResponse;
import com.example.springbootfirebase.enums.StudentStatus;
import com.example.springbootfirebase.mapper.StudentMapper;
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
    public List<StudentResponse> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return StudentMapper.INSTANCE.listStudentToListResponse(students);
    }

    @Override
    public StudentResponse saveStudent(StudentRequest student) {
        Student studentWithId = StudentMapper.INSTANCE.requestToDomain(student)
                .generateUUID();
        Student saved = studentRepository.save(studentWithId);
        return StudentMapper.INSTANCE.domainToResponse(saved);
    }

    @Override
    public StudentResponse getStudentById(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data Not Found"));
        return StudentMapper.INSTANCE.domainToResponse(student);
    }

    @Override
    public StudentResponse updateStudent(StudentRequest student) {
        Student dbStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Bad Request"));

        Student updated = dbStudent.toBuilder()
                .nis(student.getNis())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .status(student.getStatus())
                .email(student.getEmail())
                .build();

        Student saved = studentRepository.save(updated);
        return StudentMapper.INSTANCE.domainToResponse(saved);
    }

    @Override
    public String deleteStudent(String id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bad Request"));

        return studentRepository.delete(dbStudent.getId());
    }

    @Override
    public List<StudentResponse> getStudentStatusActive() {
        List<Student> studentsActive = studentRepository.findByField(Student.STATUS, StudentStatus.ACTIVE.name());
        return StudentMapper.INSTANCE.listStudentToListResponse(studentsActive);
    }
}
