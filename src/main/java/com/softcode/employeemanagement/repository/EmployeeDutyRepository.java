package com.softcode.employeemanagement.repository;

import com.softcode.employeemanagement.entity.EmployeeDutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface EmployeeDutyRepository extends JpaRepository<EmployeeDutyEntity, Integer> {

    List<EmployeeDutyEntity>  findAllByDutyStartAfterAndDutyEndBefore(OffsetDateTime dutyStart, OffsetDateTime dutyEnd);

    @Query("select ed from EmployeeDutyEntity ed where ed.employee.id= :employeeId")
    List<EmployeeDutyEntity> getAllEmployeeDuties(@Param("employeeId") Integer employeeId);

}
