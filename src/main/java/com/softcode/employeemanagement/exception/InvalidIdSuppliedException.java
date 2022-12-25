package com.softcode.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidIdSuppliedException extends RuntimeException{

    public InvalidIdSuppliedException() {
        super("Invalid Id Supplied");
    }
}
