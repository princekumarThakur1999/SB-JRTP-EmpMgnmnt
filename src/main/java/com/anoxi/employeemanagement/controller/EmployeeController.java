package com.anoxi.employeemanagement.controller;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import com.anoxi.employeemanagement.model.Employee;
import com.anoxi.employeemanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeDTO employeeDTO){
        logger.info("Received Registeration request for employee : {}" ,employeeDTO.getFullName());
        Employee registerEmp = employeeService.registerEmployee(employeeDTO);
        logger.info("Employee Registeration completed for : {}", employeeDTO.getFullName());

        return ResponseEntity.ok(registerEmp);
    }

}
