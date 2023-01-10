package com.softcode.employeemanagement.repository;

import com.softcode.employeemanagement.entity.EmployeeEntity;
import com.softcode.employeemanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByUser(UserEntity user);
}
