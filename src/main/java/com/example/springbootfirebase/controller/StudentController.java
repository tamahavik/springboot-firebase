package com.example.springbootfirebase.controller;

import com.example.springbootfirebase.dto.StudentRequest;
import com.example.springbootfirebase.dto.StudentResponse;
import com.example.springbootfirebase.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    @ResponseBody
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> response = studentService.getAllStudent();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest student) {
        StudentResponse response = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<StudentResponse> getStudentById(@RequestParam String id) {
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentRequest student) {
        StudentResponse response = studentService.updateStudent(student);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<String> deleteStudent(@RequestParam String id) {
        String response = studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(response);
    }

    @GetMapping(value = "/student/active")
    @ResponseBody
    public ResponseEntity<List<StudentResponse>> getAllActiveStudent() {
        List<StudentResponse> response = studentService.getStudentStatusActive();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
