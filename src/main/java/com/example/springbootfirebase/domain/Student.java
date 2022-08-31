package com.example.springbootfirebase.domain;

import com.example.springbootfirebase.enums.StudentStatus;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student {

    public static final String ID = "id";
    public static final String NIS = "nis";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";

    private String id;
    private String nis;
    private String firstName;
    private String lastName;
    private String email;
    private StudentStatus status;

    public Student generateUUID() {
        this.id = UUID.randomUUID().toString();
        return this;
    }
}
