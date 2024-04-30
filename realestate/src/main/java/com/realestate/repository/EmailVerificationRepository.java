package com.realestate.repository;

import com.realestate.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Integer> {


    EmailVerification findByEmail(String email);
}
