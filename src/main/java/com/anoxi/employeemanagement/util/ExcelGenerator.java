package com.anoxi.employeemanagement.util;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    private static Logger logger = LoggerFactory.getLogger(ExcelGenerator.class);

    //taking data for employeeData and convert into excel
    public ByteArrayInputStream employeeToExcel(List<EmployeeDTO> employees){
        try(Workbook workbook = new XSSFWorkbook()) {

            //create a sheet
            Sheet sheet = workbook.createSheet("Employees");

            //Header Style
            CellStyle headerStyle = workbook.createCellStyle(); //learn how to use different differnt headerstyle we can modify
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            //Header Row
            Row headerRow = sheet.createRow(0);
            String[] column = {"Full Name", "Email", "Department", "Position", "Hire Date", "Salary"};

            for(int i =0; i< column.length;i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(column[i]);
                cell.setCellStyle(headerStyle);
            }

            //data row
            int rowNum = 1;
            for(EmployeeDTO employee : employees){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(employee.getFullName());
                row.createCell(1).setCellValue(employee.getEmail());
                row.createCell(2).setCellValue(employee.getDepartment());
                row.createCell(3).setCellValue(employee.getPosition());
                row.createCell(4).setCellValue(employee.getHiredate());
                row.createCell(5).setCellValue(employee.getSalary());
            }

            //Auto-size column
            for(int i=0; i<column.length;i++){
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());  //Need to understand this step


        } catch (IOException e) {
            logger.error("Error generating excel file", e);
            throw new RuntimeException("Failed to generate excel file : " + e.getMessage());
        }


    }
}
