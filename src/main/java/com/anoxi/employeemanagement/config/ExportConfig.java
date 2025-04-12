package com.anoxi.employeemanagement.config;

import com.anoxi.employeemanagement.util.ExcelGenerator;
import com.anoxi.employeemanagement.util.PDFGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportConfig {

    @Bean
    public ExcelGenerator excelGenerator(){
        return new ExcelGenerator();
    }

    @Bean
    public PDFGenerator pdfGenerator(){
        return new PDFGenerator();
    }
}
