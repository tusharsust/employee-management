package com.softcode.employeemanagement.service;


import com.softcode.employeemanagement.model.EmployeeDuty;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public interface DutyChangeEventConsumerService {

    @RabbitHandler
    void dutyChangeEventHandler(EmployeeDuty employeeDuty);
}
