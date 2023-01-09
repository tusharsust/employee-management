package com.softcode.employeemanagement.model;

import lombok.Data;

@Data
public class SignUpDto {

    private String name;
    private String password;
    private String email;
}
