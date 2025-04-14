package com.anoxi.employeemanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String firstName){

        logger.debug("Preparing email for : {}", toEmail);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Thank for downloading files from employeemanagement service");
        message.setText("Dear "+ firstName + ",\n\n Thanks for visiting our application."
                + "\n Thanks for dowmloading files from our EmployeeManagement Service"
                + "\n\n Best Regards, \n Prince Thakur");

        mailSender.send(message);
        logger.info("Successfully email sent to : {}", toEmail);
    }
}
