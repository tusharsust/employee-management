package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.service.DutyChangeEventConsumerService;
import com.softcode.employeemanagement.service.EmailService;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import com.softcode.employeemanagement.service.EmployeeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${queue.name}")
public class DutyChangeEventConsumerServiceImpl implements DutyChangeEventConsumerService {

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
    public void dutyChangeEventHandler(EmployeeDuty employeeDuty) {
        System.out.println("Duty Change Event: " + employeeDuty.toString());

        //TODO Need to retrieve the employee details.
        //TODO Need to retrieve the employee duty details.

        String subject = "Your duty modification notification";
        String body = String.format("Mr/Mrs. %s\nYour duty has been modified\nStartTime: %s\nEndTime: %s\nThanks.",
                "HARD_CODED_NAME", "HARDCODED_DUTY_START TIME", "HARDCODED_DUTY_END_TIME");

        emailService.sendEmail("tushar.sust@gmail.com", subject, body);

    }
}
