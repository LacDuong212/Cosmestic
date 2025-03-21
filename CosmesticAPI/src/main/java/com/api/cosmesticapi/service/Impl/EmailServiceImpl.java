package com.api.cosmesticapi.service.Impl;

import com.api.cosmesticapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {     // 22110394 - Ong Vĩnh Phát
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
    @Override
    public boolean sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public boolean sendOtp(String email, String otp) {
        String subject = "Password Reset OTP";
        String message = "Your OTP for password reset is: " + otp;
        return sendEmail(email, subject, message);
    }
}
