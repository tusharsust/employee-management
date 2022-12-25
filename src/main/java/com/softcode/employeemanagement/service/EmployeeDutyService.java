package com.softcode.employeemanagement.service;

import com.softcode.employeemanagement.model.EmployeeDuty;

import java.time.OffsetDateTime;
import java.util.List;

public interface EmployeeDutyService {
    List<EmployeeDuty> getEmployeeDuties(OffsetDateTime dutyStart, OffsetDateTime dutyEnd);
    EmployeeDuty createEmployeeDuty(EmployeeDuty employeeDuty);
}
