package com.api.cosmesticapi.service;


import com.api.cosmesticapi.DTO.OtpVerificationRequest;
import com.api.cosmesticapi.DTO.RegistrationRequest;
import com.api.cosmesticapi.DTO.RegistrationResponse;
import com.api.cosmesticapi.DTO.VerificationResponse;
import com.api.cosmesticapi.entity.OTP;
import com.api.cosmesticapi.entity.User;
import com.api.cosmesticapi.repository.OTPRepository;
import com.api.cosmesticapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    public RegistrationResponse registerUser(RegistrationRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return new RegistrationResponse(false, "Username already exists", null);
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new RegistrationResponse(false, "Email already exists", null);
        }

        // Create new user (initially inactive)
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setActive(false); // Set user as inactive until OTP verification

        // Save user
        userRepository.save(user);

        // Generate and send OTP
        String otp = generateOtp();
        saveOtp(request.getEmail(), otp);
        emailService.sendOTPEmail(request.getEmail(), otp);

        return new RegistrationResponse(true,
                "Registration successful. Please check your email for verification code.",
                request.getEmail());
    }

    public VerificationResponse verifyOtp(OtpVerificationRequest request) {
        // Find valid OTP
        Optional<OTP> otpOptional = otpRepository.findByEmailAndCode(
                request.getEmail(), request.getOtp());

        if (otpOptional.isEmpty()) {
            return new VerificationResponse(false, "Invalid or expired verification code");
        }

        // Mark OTP as used
        OTP otp = otpOptional.get();
        otp.setVerified(true);
        otpRepository.save(otp);

        // Find and activate user
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return new VerificationResponse(false, "User not found");
        }

        User user = userOptional.get();
        user.setActive(true);
        userRepository.save(user);

        return new VerificationResponse(true, "Account successfully activated! You can now login.");
    }

    public RegistrationResponse resendOtp(String email) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new RegistrationResponse(false, "User not found", null);
        }

        // Check if user is already active
        User user = userOptional.get();
        if (user.isActive()) {
            return new RegistrationResponse(false, "Account is already activated", null);
        }

        // Generate and send new OTP
        String otp = generateOtp();
        saveOtp(email, otp);
        emailService.sendOTPEmail(email, otp);

        return new RegistrationResponse(true,
                "Verification code has been resent to your email", email);
    }

    private String generateOtp() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }

    private void saveOtp(String email, String otp) {
        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setCode(otp);
        otpEntity.setExpiryDate(LocalDateTime.now().plusMinutes(10)); // 10 minute expiry
        otpEntity.setVerified(false);
        otpRepository.save(otpEntity);
    }

}
