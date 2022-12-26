package com.softcode.employeemanagement.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeEntityTest {

    EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        employeeEntity = new EmployeeEntity();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        Integer idValue = 1;
        employeeEntity.setId(idValue);
        assertEquals(idValue, employeeEntity.getId());
    }

    @Test
    void getName() {
        String name = "Tushar";
        employeeEntity.setName(name);
        assertEquals(name, employeeEntity.getName());
    }

    @Test
    void getPhone() {
        String phone = "+8801776198791";
        employeeEntity.setPhone(phone);
        assertEquals(phone, employeeEntity.getPhone());
    }

    @Test
    void getEmail() {
        String email = "test@example.com";
        employeeEntity.setEmail(email);
        assertEquals(email, employeeEntity.getEmail());
    }

    @Test
    void getJoiningDate() {
        OffsetDateTime joiningDate = new Date().toInstant().atOffset(ZoneOffset.UTC);
        employeeEntity.setJoiningDate(joiningDate);
        assertEquals(joiningDate, employeeEntity.getJoiningDate());
    }

    @Test
    void getEmployeeDuties() {
        Set<EmployeeDutyEntity> employeeDutyEntities = new HashSet<>();
        EmployeeDutyEntity employeeDutyEntity = new EmployeeDutyEntity();
        employeeDutyEntity.setId(2);
        employeeDutyEntity.setDutyStart(new Date().toInstant().atOffset(ZoneOffset.UTC));
        employeeDutyEntity.setDutyEnd(new Date().toInstant().atOffset(ZoneOffset.UTC));

        employeeDutyEntities.add(employeeDutyEntity);

        employeeEntity.setEmployeeDuties(employeeDutyEntities);

        assertEquals(employeeDutyEntities, employeeEntity.getEmployeeDuties());
    }

}