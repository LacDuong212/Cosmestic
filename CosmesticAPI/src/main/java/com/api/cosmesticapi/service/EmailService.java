package com.api.cosmesticapi.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

public interface EmailService { // 22110394 - Ong Vĩnh Phát
    void sendOTPEmail(String toEmail, String otp);
    boolean sendEmail(String to, String subject, String text);
    String generateOtp();
    boolean sendOtp(String email, String otp);
}
