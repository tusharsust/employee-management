package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(SpringTemplateEngine templateEngine, JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    private static final String NAME = "name";
    private static final String START_TIME= "startTime";
    private static final String END_TIME = "endTime";
    private static final String LINE1 = "line1";



    @Override
    public void sendDutyChangeEmail(String recipient, String subject, String name, String dutyStartTime, String dutyEndTime, String emailLine) {
        Context context = new Context();
        context.setVariable(NAME, "Dear " + name + ",");
        context.setVariable(START_TIME, dutyStartTime);
        context.setVariable(END_TIME, dutyEndTime);
        context.setVariable(LINE1, emailLine);
        String body = templateEngine.process("mail/dutyNotificationChangeEmail.html", context);


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            message.setTo(recipient);
            message.setFrom(sender);
            message.setSubject(subject);
            message.setText(body, true);
            mailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", recipient);
        }  catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", recipient, e);
        }
    }
}
