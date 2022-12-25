package com.softcode.employeemanagement.repository;

import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface EmployeeDutyRepository extends JpaRepository<EmployeeDutyEntity, Integer> {

    List<EmployeeDutyEntity>  findAllByDutyStartAfterAndDutyEndBefore(OffsetDateTime dutyStart, OffsetDateTime dutyEnd);
}
