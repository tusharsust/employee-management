package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.model.Employee;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository, modelMapper);
    }

    @Test
    void getEmployees() {

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(1).build();
        List<EmployeeEntity> employeeEntitiesData = new ArrayList<>();
        employeeEntitiesData.add(employeeEntity);

        Employee employee = new Employee();
        employee.setId(1);
        List<Employee> employeesData = new ArrayList<>();
        employeesData.add(employee);

        when(employeeRepository.findAll()).thenReturn(employeeEntitiesData);
        when(modelMapper.map(any(), eq(Employee.class))).thenReturn(employee);

        List<Employee> employees = employeeService.getEmployees();

        assertEquals(1, employees.size());
        verify(employeeRepository, times(1)).findAll();
        verify(modelMapper).map(employeeEntity, Employee.class);
        assertEquals(employeesData.get(0).getId(), employees.get(0).getId());

    }

    @Test
    void createEmployee() {

        EmployeeEntity employeeEntity = EmployeeEntity.builder().build();
        EmployeeEntity savedEmployeeEntity = EmployeeEntity.builder().id(1).build();

        Employee employee = new Employee();
        employee.setId(1);

        List<Employee> employeesData = new ArrayList<>();
        employeesData.add(employee);

        when(employeeRepository.save(any())).thenReturn(savedEmployeeEntity);
        when(modelMapper.map(any(), eq(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(modelMapper.map(any(), eq(Employee.class))).thenReturn(employee);

        Employee savedEmployed = employeeService.createEmployee(employee);

        assertEquals(1, savedEmployed.getId());
        verify(employeeRepository, times(1)).save(employeeEntity);
        verify(modelMapper, times(1)).map(employee, EmployeeEntity.class);
        verify(modelMapper, times(1)).map(savedEmployeeEntity, Employee.class);

    }

    @Test
    void updateEmployee() {

    }
}