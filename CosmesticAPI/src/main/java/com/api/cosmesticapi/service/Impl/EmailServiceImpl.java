package com.api.cosmesticapi.service.Impl;

import com.api.cosmesticapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendOTPEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP for Account Activation");
        message.setText("Your OTP for account activation is: " + otp +
                ". This code will expire in 10 minutes.");

        mailSender.send(message);
    }
}
