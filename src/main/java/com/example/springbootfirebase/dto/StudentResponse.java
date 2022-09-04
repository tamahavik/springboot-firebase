package com.example.springbootfirebase.dto;

import com.example.springbootfirebase.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private String id;
    private String nis;
    private String firstName;
    private String lastName;
    private StudentStatus status;
}
