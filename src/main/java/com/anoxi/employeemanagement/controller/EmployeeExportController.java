package com.anoxi.employeemanagement.controller;

import com.anoxi.employeemanagement.service.EmployeeService;
import com.anoxi.employeemanagement.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/export")
public class EmployeeExportController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeExportController.class);

    private final ExportService exportService;

    @Autowired
    public EmployeeExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/employees/excel")
    public ResponseEntity<InputStreamResource> exportAllEmployeesToExcel(){
        logger.info("Receive Request to export all employees to Excel.");
        ByteArrayInputStream inputStream = exportService.exportEmployeesToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=all_employees.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/employees/pdf")
    public ResponseEntity<InputStreamResource> exportAllEmployeesToPdf(){
        logger.info("Receive Request to export all employees to Pdf.");
        ByteArrayInputStream inputStream = exportService.exportEmployeesToPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=all_employees.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/employees/{dept}/excel")
    public ResponseEntity<InputStreamResource> exportDeptEmployeesToExcel(@PathVariable("dept") String department){
        logger.info("Receive request to export {} department employees to Excel", department);
        ByteArrayInputStream inputStream = exportService.exportDepartmentEmployeesToExcel(department);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+ department + "_employees.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/employees/{dept}/pdf")
    public ResponseEntity<InputStreamResource> exportDeptEmployeesToPdf(@PathVariable("dept") String department){
        logger.info("Receive request to export {} department employees to Pdf", department);
        ByteArrayInputStream inputStream = exportService.exportDepartmentEmployeesToPdf(department);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+ department + "_employees.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
