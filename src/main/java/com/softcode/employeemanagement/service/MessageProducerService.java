package com.softcode.employeemanagement.service;

import com.softcode.employeemanagement.model.EmployeeDuty;

public interface MessageProducerService {
    String produceDutyChangeMessage(EmployeeDuty employeeDuty);

}
