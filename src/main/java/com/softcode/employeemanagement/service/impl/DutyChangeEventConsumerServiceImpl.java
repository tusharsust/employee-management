package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.service.DutyChangeEventConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${queue.name}")
public class DutyChangeEventConsumerServiceImpl implements DutyChangeEventConsumerService {

    @Override
    public void dutyChangeEventHandler(EmployeeDuty employeeDuty) {
        System.out.println("Duty Change Event: " + employeeDuty.toString());
    }
}
