package com.softcode.employeemanagement.service.impl;

import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.exception.EmployeeDutyNotFoundException;
import com.softcode.employeemanagement.exception.EmployeeNotFoundException;
import com.softcode.employeemanagement.exception.InvalidIdSuppliedException;
import com.softcode.employeemanagement.model.DutyChangeEvent;
import com.softcode.employeemanagement.model.DutyChangeType;
import com.softcode.employeemanagement.model.EmployeeDuty;
import com.softcode.employeemanagement.repository.EmployeeDutyRepository;
import com.softcode.employeemanagement.repository.EmployeeRepository;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import com.softcode.employeemanagement.service.MessageProducerService;
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
    private final MessageProducerService messageProducerService;

    public EmployeeDutyServiceImpl(EmployeeDutyRepository employeeDutyRepository,
                                   EmployeeRepository employeeRepository,
                                   ModelMapper modelMapper,
                                   MessageProducerService messageProducerService) {
        this.employeeDutyRepository = employeeDutyRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.messageProducerService = messageProducerService;
    }

    @Override
    public List<EmployeeDuty> getEmployeeDuties(OffsetDateTime dutyStart, OffsetDateTime dutyEnd) {
        if (dutyStart != null && dutyEnd != null) {
            return employeeDutyRepository.findAllByDutyStartAfterAndDutyEndBefore(dutyStart, dutyEnd).stream().map(this::mapToDto).collect(Collectors.toList());
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
        DutyChangeEvent dutyChangeEvent = DutyChangeEvent.builder()
                .employeeDutyId(newEmployeeDuty.getId())
                .dutyChangeType(DutyChangeType.CREATED)
                .build();

        messageProducerService.produceDutyChangeMessage(dutyChangeEvent);

        return newEmployeeDuty;
    }

    @Override
    public EmployeeDuty getEmployeeDutyById(Integer id) {

        if (id == null || id <= 0) {
            throw new InvalidIdSuppliedException();
        }

        EmployeeDutyEntity employeeDutyEntity = employeeDutyRepository.findById(id).orElseThrow(EmployeeDutyNotFoundException::new);
        return mapToDto(employeeDutyEntity);
    }

    @Override
    public void deleteEmployeeDutyById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdSuppliedException();
        }

        if (employeeDutyRepository.findById(id).isEmpty()) {
            throw new EmployeeDutyNotFoundException();
        }
    }

    @Override
    public EmployeeDuty updateEmployeeDuty(EmployeeDuty employeeDuty) {

        if (employeeDuty.getId() == null || employeeDuty.getId() <= 0) {
            throw new InvalidIdSuppliedException();
        }
        EmployeeDutyEntity dbEmployeeDutyEntity = employeeDutyRepository.findById(employeeDuty.getId()).orElseThrow(EmployeeDutyNotFoundException::new);

        if (employeeDutyRepository.findById(dbEmployeeDutyEntity.getId()).isEmpty()) {
            throw new EmployeeDutyNotFoundException();
        }
        if (employeeRepository.findById(employeeDuty.getEmployeeId()).isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        EmployeeDuty newEmployeeDuty = mapToDto(employeeDutyRepository.save(mapToEntity(employeeDuty)));

        DutyChangeEvent dutyChangeEvent = DutyChangeEvent.builder()
                .employeeDutyId(newEmployeeDuty.getId())
                .dutyChangeType(DutyChangeType.UPDATED)
                .build();

        messageProducerService.produceDutyChangeMessage(dutyChangeEvent);

        return employeeDuty;
    }

    private EmployeeDuty mapToDto(EmployeeDutyEntity employeeDutyEntity) {
        return modelMapper.map(employeeDutyEntity, EmployeeDuty.class);
    }

    private EmployeeDutyEntity mapToEntity(EmployeeDuty employeeDuty) {
        return modelMapper.map(employeeDuty, EmployeeDutyEntity.class);
    }
}
