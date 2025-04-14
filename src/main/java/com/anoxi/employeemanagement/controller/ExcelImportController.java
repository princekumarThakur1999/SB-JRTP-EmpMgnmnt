package com.anoxi.employeemanagement.controller;

import com.anoxi.employeemanagement.service.ExcelImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
public class ExcelImportController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelImportController.class);

    @Autowired
    private ExcelImportService excelImportService;

    @PostMapping("/employees/excel")
    public ResponseEntity<String> importEmployeesFromExcel(@RequestParam("file")MultipartFile file){
        logger.info("Received Request to import employees from excel file : {} " , file.getOriginalFilename());

        try{
            excelImportService.importFromExcel(file);
            return ResponseEntity.ok("Successfully imported employees from Excel file: " + file.getOriginalFilename());
        } catch (Exception e) {
            logger.error("Error Importing employees: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import employees: "+ e.getMessage());

        }

    }
}
