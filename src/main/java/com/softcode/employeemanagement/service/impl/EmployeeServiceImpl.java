package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.model.Employee;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import com.softcode.employeemanagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private Employee mapToDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, Employee.class);
    }

    private EmployeeEntity mapToEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeEntity.class);
    }
}
