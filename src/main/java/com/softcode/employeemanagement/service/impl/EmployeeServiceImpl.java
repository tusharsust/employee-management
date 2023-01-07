package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.exception.EmployeeNotFoundException;
import com.softcode.employeemanagement.exception.InvalidIdSuppliedException;
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
    public List<Employee> getEmployees() {
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity savedEmployee =  employeeRepository.save(mapToEntity(employee));
        return mapToDto(savedEmployee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        if (employee.getId() == null || employee.getId() <= 0) {
            throw new InvalidIdSuppliedException();
        }
        if (employeeRepository.findById(employee.getId()).isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(mapToEntity(employee));
        return mapToDto(updatedEmployeeEntity);
    }

    @Override
    public Employee getEmployeeById(Integer id) {

        if (id == null || id <= 0) {
            throw new InvalidIdSuppliedException();
        }

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);

        return mapToDto(employeeEntity);
    }

    @Override
    public void deleteEmployee(Integer id) {

        if (id == null || id <= 0) {
            throw new InvalidIdSuppliedException();
        }
        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
    }

    private Employee mapToDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, Employee.class);
    }

    private EmployeeEntity mapToEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeEntity.class);
    }
}
