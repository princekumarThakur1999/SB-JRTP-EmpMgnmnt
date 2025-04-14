package com.anoxi.employeemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String department;
    private String position;
    private LocalDate hiredate;
    private Float salary;

    public String getFullName(){

        return (firstName != null && lastName != null)? firstName +" "+ lastName : null;
    }
}
