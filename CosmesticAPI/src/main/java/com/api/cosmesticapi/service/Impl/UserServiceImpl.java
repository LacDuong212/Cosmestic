// 22110394 - Ong Vĩnh Phát
package com.api.cosmesticapi.service.Impl;

import com.api.cosmesticapi.dto.*;
import com.api.cosmesticapi.entity.OTP;
import com.api.cosmesticapi.entity.User;
import com.api.cosmesticapi.repository.OTPRepository;
import com.api.cosmesticapi.repository.UserRepository;
import com.api.cosmesticapi.service.EmailService;
import com.api.cosmesticapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ApiResponse registerUser(RegisterRequest request) {
        // Validate input
        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false, "Email already in use");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse(false, "Username already taken");
        }

        // Create user with inactive status
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setActive(false); // Set user as inactive until verification

        userRepository.save(user);

        // Generate and send OTP
        String otp = generateOTP();
        OTP otpEntity = new OTP();
        otpEntity.setEmail(request.getEmail());
        otpEntity.setCode(otp);
        otpEntity.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        otpEntity.setVerified(false);

        otpRepository.save(otpEntity);

        // Send OTP via email
        emailService.sendOTPEmail(request.getEmail(), otp);

        return new ApiResponse(true, "User registered successfully. Please verify your email with the OTP sent.");

    }

    @Override
    public ApiResponse verifyOTP(VerifyOTPRequest request) {
        Optional<OTP> otpOptional = otpRepository.findByEmailAndCode(request.getEmail(), request.getOtp());

        if (otpOptional.isEmpty()) {
            return new ApiResponse(false, "Invalid OTP");
        }

        OTP otp = otpOptional.get();

        if (otp.isVerified()) {
            return new ApiResponse(false, "OTP already verified");
        }

        if (otp.getExpiryDate().isBefore(LocalDateTime.now())) {
            return new ApiResponse(false, "OTP expired");
        }

        // Activate user
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(true);
            userRepository.save(user);

            // Mark OTP as verified
            otp.setVerified(true);
            otpRepository.save(otp);

            return new ApiResponse(true, "Account activated successfully");
        }

        return new ApiResponse(false, "User not found");
    }

    @Override
    public String generateOTP() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public LoginResponse login(LoginRequest request) { // 22110410 - Huỳnh Thị Mỹ Tâm
        // Try to find user by username or email
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            // If not found by username, try email
            userOptional = userRepository.findByEmail(request.getUsername());
        }

        if (userOptional.isEmpty()) {
            return new LoginResponse(false, "Invalid credentials", null);
        }

        User user = userOptional.get();

        // Check if user is active (has verified email)
        if (!user.isActive()) {
            return new LoginResponse(false, "Account not activated. Please verify your email.", null);
        }

        // Verify password
        if (user.getPassword().equals(request.getPassword())) {
            // Don't send password to client
            user.setPassword(null);
            return new LoginResponse(true, "Login successful", user);
        } else {
            return new LoginResponse(false, "Invalid credentials", null);
        }

    }
}
