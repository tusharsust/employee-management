package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.model.DutyChangeEvent;
import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.service.MessageProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerServiceImpl implements MessageProducerService {

    private final AmqpTemplate template;

    @Value("${exchange.name}")
    private String exchange;

    @Value("${routing.key}")
    private String routingKey;

    public MessageProducerServiceImpl(AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public String produceDutyChangeMessage(DutyChangeEvent dutyChangeEvent) {
        template.convertAndSend(exchange, routingKey, dutyChangeEvent);
        return "OK! Message has been produced successfully!";
    }
}
