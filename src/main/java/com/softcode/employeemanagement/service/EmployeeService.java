package com.softcode.employeemanagement.service;

import com.softcode.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
}
