package com.example.springbootfirebase.mapper;

import com.example.springbootfirebase.domain.Student;
import com.example.springbootfirebase.dto.StudentRequest;
import com.example.springbootfirebase.dto.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student requestToDomain(StudentRequest request);

    StudentResponse domainToResponse(Student domain);

    List<StudentResponse> listStudentToListResponse(List<Student> listDomain);
}
