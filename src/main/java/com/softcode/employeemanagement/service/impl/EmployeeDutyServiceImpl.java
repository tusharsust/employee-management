package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.repository.EmployeeDutyRepository;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDutyServiceImpl implements EmployeeDutyService {

    private final EmployeeDutyRepository employeeDutyRepository;

    public EmployeeDutyServiceImpl(EmployeeDutyRepository employeeDutyRepository) {
        this.employeeDutyRepository = employeeDutyRepository;
    }
}
