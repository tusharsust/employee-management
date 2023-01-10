package com.softcode.employeemanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    private OffsetDateTime joiningDate;

    private String deviceToken;

    @OneToOne
    @JoinColumn(unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeDutyEntity> employeeDuties = new HashSet<>();

}
