package com.softcode.employeemanagement.service;

import com.softcode.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    void deleteEmployee(Integer id);
}
