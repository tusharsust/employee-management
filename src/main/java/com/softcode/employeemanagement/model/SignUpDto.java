package com.softcode.employeemanagement.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SignUpDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private OffsetDateTime joiningDate;
}
