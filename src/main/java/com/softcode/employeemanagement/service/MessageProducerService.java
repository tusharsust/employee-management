package com.softcode.employeemanagement.service;


import com.softcode.employeemanagement.model.DutyChangeEvent;

public interface MessageProducerService {
    String produceDutyChangeMessage(DutyChangeEvent dutyChangeEvent);

}
