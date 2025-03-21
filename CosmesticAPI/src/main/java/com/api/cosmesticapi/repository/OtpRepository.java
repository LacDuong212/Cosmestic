package com.api.cosmesticapi.repository;

import com.api.cosmesticapi.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmailAndCodeAndUsedFalseAndExpiryDateAfter(
            String email, String code, LocalDateTime now);

    List<Otp> findByEmailAndUsedFalseAndExpiryDateAfter(
            String email, LocalDateTime now);
}
