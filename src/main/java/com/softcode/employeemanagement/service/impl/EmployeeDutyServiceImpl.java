package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.exception.EmployeeNotFoundException;
import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.repository.EmployeeDutyRepository;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDutyServiceImpl implements EmployeeDutyService {

    private final EmployeeDutyRepository employeeDutyRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDutyServiceImpl(EmployeeDutyRepository employeeDutyRepository,
                                   EmployeeRepository employeeRepository,
                                   ModelMapper modelMapper) {
        this.employeeDutyRepository = employeeDutyRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeDuty> getEmployeeDuties(OffsetDateTime dutyStart, OffsetDateTime dutyEnd) {
        if (dutyStart != null  && dutyEnd != null) {
            return employeeDutyRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
        } else {
            return employeeDutyRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
        }
    }

    @Override
    public EmployeeDuty createEmployeeDuty(EmployeeDuty employeeDuty) {

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDuty.getEmployeeId()).orElseThrow(EmployeeNotFoundException::new);

        EmployeeDutyEntity employeeDutyEntity = mapToEntity(employeeDuty);
        employeeDutyEntity.setEmployee(employeeEntity);

        EmployeeDuty newEmployeeDuty = mapToDto(employeeDutyRepository.save(employeeDutyEntity));

        return newEmployeeDuty;
    }

    private EmployeeDuty mapToDto(EmployeeDutyEntity employeeDutyEntity) {
        return modelMapper.map(employeeDutyEntity, EmployeeDuty.class);
    }

    private EmployeeDutyEntity mapToEntity(EmployeeDuty employeeDuty) {
        return modelMapper.map(employeeDuty, EmployeeDutyEntity.class);
    }
}
