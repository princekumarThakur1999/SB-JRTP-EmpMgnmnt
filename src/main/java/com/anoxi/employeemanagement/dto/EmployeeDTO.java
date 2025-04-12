package com.anoxi.employeemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDTO {

    private String fullName;
    private String email;
    private String department;
    private String position;
    private LocalDate hiredate;
    private Float salary;

}
