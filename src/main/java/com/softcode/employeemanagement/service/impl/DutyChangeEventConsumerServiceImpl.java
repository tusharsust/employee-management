package com.softcode.employeemanagement.service.impl;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.softcode.employeemanagement.model.*;
import com.softcode.employeemanagement.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${queue.name}")
public class DutyChangeEventConsumerServiceImpl implements DutyChangeEventConsumerService {

    private final Logger log = LoggerFactory.getLogger(DutyChangeEventConsumerServiceImpl.class);

    private final EmailService emailService;
    private final EmployeeService employeeService;
    private final EmployeeDutyService employeeDutyService;
    private final FirebaseMessagingService firebaseMessagingService;

    public DutyChangeEventConsumerServiceImpl(EmailService emailService,
                                              EmployeeService employeeService,
                                              EmployeeDutyService employeeDutyService,
                                              FirebaseMessagingService firebaseMessagingService) {
        this.emailService = emailService;
        this.employeeService = employeeService;
        this.employeeDutyService = employeeDutyService;
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @Override
    public void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent) {

        log.debug("Duty Change Event Received: EmployeeDuty Id: {} Event Type: {}", dutyChangeEvent.getEmployeeDutyId(), dutyChangeEvent.getDutyChangeType());

        EmployeeDuty dbEmployeeDuty = employeeDutyService.getEmployeeDutyById(dutyChangeEvent.getEmployeeDutyId());
        Employee employee = employeeService.getEmployeeById(dbEmployeeDuty.getEmployeeId());

        //Send mail
//        sendMail(employee, dbEmployeeDuty, dutyChangeEvent.getDutyChangeType());

        //Send Push Notification
        sendPushNotification(employee, dbEmployeeDuty, dutyChangeEvent.getDutyChangeType());
    }

    private void sendPushNotification(Employee employee, EmployeeDuty employeeDuty, DutyChangeType dutyChangeType) {

        Note note = Note.builder()
                .content(dutyChangeType == DutyChangeType.CREATED ? "New duty have been added to you. Please check your schedule." : "Your duty has been changed. Please check your schedule.")
                .subject(dutyChangeType == DutyChangeType.CREATED ? "Duty added notification!" : "Duty added notification!")
                .build();
        String deviceToken = "diSpdASIR5W8pzHV-ifT12:APA91bFmDgmQ9v_claR6iyRS5MIbEBooZtHULj8_1qSlZy6LqOj30a1q5gMSAKpocmWk88QFtjEQs9w2XaLUnYtxJyXr7fgiyBMsyrHdzHyL710FVmioF86QZIZz-qToa64hPnJLkL4z"; // it should be comes from employee object

        try {
            firebaseMessagingService.sendNotification(note, deviceToken);
        } catch (FirebaseMessagingException e) {
            log.warn("Push notification could not be sent to user '{}'", employee.getEmail(), e);
        }

    }

    private void sendMail(Employee employee, EmployeeDuty employeeDuty, DutyChangeType dutyChangeType) {
        String subject;
        String emailLine;

        if (dutyChangeType == DutyChangeType.CREATED) {
            subject = "Duty added notification!";
            emailLine = "Please note that the following duty schedule has been assigned to you";
        } else {
            subject = "Duty updated notification!";
            emailLine = "Please note that the following duty schedule of you has been updated";
        }

        emailService.sendDutyChangeEmail(employee.getEmail(), subject, employee.getName(), employeeDuty.getDutyStart().toString(), employeeDuty.getDutyEnd().toString(), emailLine);
    }
}
