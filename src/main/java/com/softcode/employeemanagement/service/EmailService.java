package com.softcode.employeemanagement.service;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body);
}
