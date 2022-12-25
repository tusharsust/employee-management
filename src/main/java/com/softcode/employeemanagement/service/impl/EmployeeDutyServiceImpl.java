package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.repository.EmployeeDutyRepository;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDutyServiceImpl implements EmployeeDutyService {

    private final EmployeeDutyRepository employeeDutyRepository;
    private final ModelMapper modelMapper;

    public EmployeeDutyServiceImpl(EmployeeDutyRepository employeeDutyRepository, ModelMapper modelMapper) {
        this.employeeDutyRepository = employeeDutyRepository;
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

    private EmployeeDuty mapToDto(EmployeeDutyEntity employeeDutyEntity) {
        return modelMapper.map(employeeDutyEntity, EmployeeDuty.class);
    }

    private EmployeeDutyEntity mapToEntity(EmployeeDuty employeeDuty) {
        return modelMapper.map(employeeDuty, EmployeeDutyEntity.class);
    }
}