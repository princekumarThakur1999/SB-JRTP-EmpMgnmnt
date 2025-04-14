package com.anoxi.employeemanagement.service;

import com.anoxi.employeemanagement.model.Employee;
import com.anoxi.employeemanagement.repository.EmployeeRepository;
import com.anoxi.employeemanagement.util.ExcelImportHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExcelImportService {

    private final static Logger logger = LoggerFactory.getLogger(ExcelImportService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public void importFromExcel(MultipartFile file){
        logger.info("Start Excel import process");
        try{
            List<Employee> employees = ExcelImportHelper.excelToDatabase(file);
            employeeRepository.saveAll(employees);
            logger.info("Sucessfully imported {} employees to database", employees.size());
        } catch (Exception e) {
            logger.error("Error during Excel import: {}", e);
            throw new RuntimeException("Could not import excel data "+e.getMessage());
        }
    }
}
