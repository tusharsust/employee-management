package com.softcode.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeDutyNotFoundException extends RuntimeException {
    public EmployeeDutyNotFoundException() {
        super("Employee duty not found");
    }
}
