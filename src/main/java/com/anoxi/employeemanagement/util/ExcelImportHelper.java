package com.anoxi.employeemanagement.util;

import com.anoxi.employeemanagement.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelImportHelper {

    private static final Logger logger = LoggerFactory.getLogger(ExcelImportHelper.class);

    //checking the files are in Excel format or not
    public static boolean hasExcelFormat(MultipartFile file){

        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType());
    }

    //import data from excel to database
    public static List<Employee> excelToDatabase(MultipartFile file){

        //using workbookfactory create a workbook and get file details using inputstream
        try(Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            //create sheet
            Sheet sheet = workbook.getSheetAt(0);
            //iterating row from the sheet
            Iterator<Row> rowIterator = sheet.iterator();

            List<Employee> employees = new ArrayList<>();

            int rowNumber = 0;
            while(rowIterator.hasNext()){
                Row currentRow = rowIterator.next();

                //skip header now
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                //from a single row iterate data from cells in row.
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();

                int cellIdx = 0;
                while(cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx){
                        case 0 -> employee.setFirstName(currentCell.getStringCellValue());
                        case 1 -> employee.setLastName(currentCell.getStringCellValue());
                        case 2 -> employee.setEmail(currentCell.getStringCellValue());
                        case 3 -> employee.setDepartment(currentCell.getStringCellValue());
                        case 4 -> employee.setPosition(currentCell.getStringCellValue());
                        case 5 -> employee.setHiredate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                        case 6 -> employee.setSalary((float) currentCell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIdx++;
                }
                employees.add(employee);
            }
            logger.info("Successsfully parsed {} employees from excel file", employees.size());
            return employees;
        } catch (IOException e) {
            logger.error("Failed to parse Excel file: {}", e.getMessage());
            throw new RuntimeException("Failed to parse Excel file : " + e.getMessage());
        }


    }
}
