package com.example.springbootfirebase.controller;

import com.example.springbootfirebase.domain.Student;
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
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> response = studentService.getAllStudent();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student response = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<Student> getStudentByNIM(@RequestParam String id) {
        Student response = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping(value = "/student")
    @ResponseBody
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student response = studentService.updateStudent(student);
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
    public ResponseEntity<List<Student>> getAllActiveStudent() {
        List<Student> response = studentService.getStudentStatusActive();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
