package com.anoxi.employeemanagement.util;

import com.anoxi.employeemanagement.dto.EmployeeDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/DD/YYYY");

    public ByteArrayInputStream employeeToPDF(List<EmployeeDTO> employees){

        //create a document object
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            PdfWriter.getInstance(document, out);
            document.open();

            //Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            //create table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            //Table Header
            String[] headers = {"Full Name", "Email", "Department", "Position", "HireDate", "Salary"};
            Font headerFont =  FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            for(String header : headers){
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                cell.setPhrase(new Phrase(header, headerFont));
                table.addCell(cell);
            }

            //Table data
            for (EmployeeDTO employee : employees){
                table.addCell(employee.getFullName());
                table.addCell(employee.getEmail());
                table.addCell(employee.getDepartment());
                table.addCell(employee.getPosition());
                table.addCell(DATE_TIME_FORMATTER.format(employee.getHiredate()));
                table.addCell(String.format("$%, .2f",employee.getSalary()));
            }

            //add table data into document
            document.add(table);
            document.close();

        } catch (DocumentException e) {

            logger.error("Error generating pdf file", e);
            throw new RuntimeException("Failed to generate pdf file: "+ e.getMessage());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
