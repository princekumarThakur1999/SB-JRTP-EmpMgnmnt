package com.anoxi.employeemanagement.service;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import com.anoxi.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    //fetch all employees form the database
    List<EmployeeDTO> getAllEmployees();

    //get employees by department name
    List<EmployeeDTO> getEmployeesByDepartment(String department);

}
