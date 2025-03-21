package com.api.cosmesticapi.repository;

import com.api.cosmesticapi.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    Optional<OTP> findByEmailAndCode(String email, String code);
    Optional<OTP> findByEmail(String email);
}
