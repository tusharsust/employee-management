package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String recipient, String subject, String body) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipient);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);

            mailSender.send(mailMessage);
            System.out.println("Mail has been send to successfully!!");
        } catch (Exception e) {
            System.out.println("Error while sending mail to " + recipient);
        }
    }
}
