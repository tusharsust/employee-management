package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.model.DutyChangeEvent;
import com.softcode.employeemanagement.model.DutyChangeType;
import com.softcode.employeemanagement.model.Employee;
import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.service.DutyChangeEventConsumerService;
import com.softcode.employeemanagement.service.EmailService;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import com.softcode.employeemanagement.service.EmployeeService;
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

    public DutyChangeEventConsumerServiceImpl(EmailService emailService,
                                              EmployeeService employeeService,
                                              EmployeeDutyService employeeDutyService) {
        this.emailService = emailService;
        this.employeeService = employeeService;
        this.employeeDutyService = employeeDutyService;
    }

    @Override
    public void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent) {

        log.debug("Duty Change Event Received: EmployeeDuty Id: {} Event Type: {}", dutyChangeEvent.getEmployeeDutyId(), dutyChangeEvent.getDutyChangeType());

        EmployeeDuty dbEmployeeDuty = employeeDutyService.getEmployeeDutyById(dutyChangeEvent.getEmployeeDutyId());
        Employee employee = employeeService.getEmployeeById(dbEmployeeDuty.getEmployeeId());

        String subject = "";
        String emailLine = "";

        if (dutyChangeEvent.getDutyChangeType() == DutyChangeType.CREATED) {
            subject = "Duty added notification!";
            emailLine = "Please note that the following duty schedule has been assigned to you";
        } else {
            subject = "Duty updated notification!";
            emailLine = "Please note that the following duty schedule of you has been updated";
        }

        emailService.sendDutyChangeEmail(employee.getEmail(), subject, employee.getName(), dbEmployeeDuty.getDutyStart().toString(), dbEmployeeDuty.getDutyEnd().toString(), emailLine);
    }
}
