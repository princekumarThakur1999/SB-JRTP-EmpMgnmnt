package com.anoxi.employeemanagement.service;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import com.anoxi.employeemanagement.model.Employee;
import com.anoxi.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //fetch all employees
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("fetching all the Employees data from the database.");
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::converToDTO).collect(Collectors.toList());  //Need to understand this line how stream is working here
    }

    //fetch employees for department
    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        logger.info("Fetching Employees for department: {}",department);
        List<Employee> employees = employeeRepository.findByDepartment(department);

        return employees.stream().map(this::converToDTO).collect(Collectors.toList());
    }

    //converting your employee entity object into employeeDTO object
    private EmployeeDTO converToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName(employee.getFirstName()+" "+employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setPosition(employee.getPosition());
        employeeDTO.setHiredate(employee.getHiredate());
        employeeDTO.setSalary(employee.getSalary());

        return employeeDTO;
    }
}
