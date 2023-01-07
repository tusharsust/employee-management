package com.softcode.employeemanagement.service;


import com.softcode.employeemanagement.model.DutyChangeEvent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public interface DutyChangeEventConsumerService {

    @RabbitHandler
    void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent);
}
