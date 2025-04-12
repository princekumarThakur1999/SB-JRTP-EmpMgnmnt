package com.anoxi.employeemanagement.service;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import com.anoxi.employeemanagement.util.ExcelGenerator;
import com.anoxi.employeemanagement.util.PDFGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
//@Data
//@RequiredArgsConstructor
public class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    private final EmployeeService employeeService;
    private final ExcelGenerator excelGenerator;
    private final PDFGenerator pdfGenerator;

    @Autowired
    public ExportService(EmployeeService employeeService, ExcelGenerator excelGenerator, PDFGenerator pdfGenerator) {
        this.employeeService = employeeService;
        this.excelGenerator = excelGenerator;
        this.pdfGenerator = pdfGenerator;
    }

    public ByteArrayInputStream exportEmployeesToExcel(){
        logger.info("Exporting all Employees to Excel Format");
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        return excelGenerator.employeeToExcel(employees);
    }

    public ByteArrayInputStream exportDepartmentEmployeesToExcel(String department){
        logger.info("Exporting {} department employees to excel format", department);
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);

        return excelGenerator.employeeToExcel(employees);
    }

    public ByteArrayInputStream exportEmployeesToPdf(){
        logger.info("Exporting all Employees to Pdf Format");
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        return pdfGenerator.employeeToPDF(employees);
    }

    public ByteArrayInputStream exportDepartmentEmployeesToPdf(String department){
        logger.info("Exporting {} department employees to Pdf format", department);
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);

        return pdfGenerator.employeeToPDF(employees);
    }
}
