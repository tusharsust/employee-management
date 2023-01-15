package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.exception.EmployeeNotFoundException;
import com.softcode.employeemanagement.exception.InvalidIdSuppliedException;
import com.softcode.employeemanagement.model.Employee;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import com.softcode.employeemanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository, modelMapper, userRepository);
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
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(1)
                .name("example@gmail.com")
                .build();

        EmployeeEntity savedEmployeeEntity = EmployeeEntity.builder().id(1)
                .name("example@gmail.com")
                .build();

        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmail("example@gmail.com");

        when(employeeRepository.save(any())).thenReturn(savedEmployeeEntity);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employeeEntity));
        when(modelMapper.map(any(), eq(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(modelMapper.map(any(), eq(Employee.class))).thenReturn(employee);

        Employee updateEmployee = employeeService.updateEmployee(employee);

        assertEquals(savedEmployeeEntity.getName(), updateEmployee.getEmail());
        verify(employeeRepository, times(1)).findById(employeeEntity.getId());
        verify(employeeRepository, times(1)).save(employeeEntity);
        verify(modelMapper, times(1)).map(savedEmployeeEntity, Employee.class);
        verify(modelMapper, times(1)).map(employee, EmployeeEntity.class);

    }

    @Test
    void updateEmployeeWithInvalidIdSuppliedException() {
        InvalidIdSuppliedException thrown = Assertions.assertThrows(InvalidIdSuppliedException.class, () -> employeeService.updateEmployee(new Employee()), "Invalid Id supplied exception was expected");

        Assertions.assertEquals("Invalid Id Supplied", thrown.getMessage());

        InvalidIdSuppliedException thrown1 = Assertions.assertThrows(InvalidIdSuppliedException.class, () -> {
            Employee employee = new Employee();
            employee.setId(-1);
            employeeService.updateEmployee(employee);
        }, "Invalid Id supplied exception was expected");

        Assertions.assertEquals("Invalid Id Supplied", thrown1.getMessage());


        EmployeeNotFoundException thrown2 = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            Employee employee = new Employee();
            employee.setId(23);
            employeeService.updateEmployee(employee);
        }, "Employee not found exception was expected");

        Assertions.assertEquals("Employee not found", thrown2.getMessage());
    }
}