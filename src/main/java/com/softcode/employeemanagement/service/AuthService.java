package com.softcode.employeemanagement.service;

import com.softcode.employeemanagement.model.LoginDto;
import com.softcode.employeemanagement.model.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(SignUpDto signUpDto);
}
