package com.softcode.employeemanagement.service.impl;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.exception.EmployeeDutyNotFoundException;
import com.softcode.employeemanagement.exception.EmployeeNotFoundException;
import com.softcode.employeemanagement.model.*;
import com.softcode.employeemanagement.repository.EmployeeDutyRepository;
import com.softcode.employeemanagement.repository.EmployeeRepository;
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
    private final FirebaseMessagingService firebaseMessagingService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeDutyRepository employeeDutyRepository;

    public DutyChangeEventConsumerServiceImpl(EmailService emailService,
                                              FirebaseMessagingService firebaseMessagingService,
                                              EmployeeRepository employeeRepository,
                                              EmployeeDutyRepository employeeDutyRepository) {
        this.emailService = emailService;
        this.firebaseMessagingService = firebaseMessagingService;
        this.employeeRepository = employeeRepository;
        this.employeeDutyRepository = employeeDutyRepository;
    }

    @Override
    public void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent) {

        log.debug("Duty Change Event Received: EmployeeDuty Id: {} Event Type: {}", dutyChangeEvent.getEmployeeDutyId(), dutyChangeEvent.getDutyChangeType());

        EmployeeDutyEntity employeeDuty = employeeDutyRepository.findById(dutyChangeEvent.getEmployeeDutyId())
                .orElseThrow(EmployeeDutyNotFoundException::new);

        EmployeeEntity employee = employeeRepository.findById(employeeDuty.getEmployee().getId())
                .orElseThrow(EmployeeNotFoundException::new);

        //Send mail
//        sendMail(employee, dbEmployeeDuty, dutyChangeEvent.getDutyChangeType());

        //Send Push Notification
        sendPushNotification(employee, employeeDuty, dutyChangeEvent.getDutyChangeType());
    }

    private void sendPushNotification(EmployeeEntity employee, EmployeeDutyEntity employeeDuty, DutyChangeType dutyChangeType) {

        Note note = Note.builder()
                .content(dutyChangeType == DutyChangeType.CREATED ? "New duty have been added to you. Please check your schedule." : "Your duty has been changed. Please check your schedule.")
                .subject(dutyChangeType == DutyChangeType.CREATED ? "Duty added notification!" : "Duty added notification!")
                .build();

        try {
            firebaseMessagingService.sendNotification(note, employee.getDeviceToken());
        } catch (FirebaseMessagingException e) {
            log.warn("Push notification could not be sent to user '{}'", employee.getUser().getEmail(), e);
        }

    }

    private void sendMail(EmployeeEntity employee, EmployeeDutyEntity employeeDuty, DutyChangeType dutyChangeType) {
        String subject;
        String emailLine;

        if (dutyChangeType == DutyChangeType.CREATED) {
            subject = "Duty added notification!";
            emailLine = "Please note that the following duty schedule has been assigned to you";
        } else {
            subject = "Duty updated notification!";
            emailLine = "Please note that the following duty schedule of you has been updated";
        }

        emailService.sendDutyChangeEmail(employee.getUser().getEmail(), subject, employee.getName(), employeeDuty.getDutyStart().toString(), employeeDuty.getDutyEnd().toString(), emailLine);
    }
}
