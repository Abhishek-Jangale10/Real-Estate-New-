package com.realestate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "otp")
    private String otp;

    @Column(name = "status")
    private String status;

    @Column(name = "creationTime")
    private LocalDateTime creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public EmailVerification(Integer id, String email, String otp, String status, LocalDateTime creationTime) {
        super();
        this.id = id;
        this.email = email;
        this.otp = otp;
        this.status = status;
        this.creationTime = creationTime;
    }

    public EmailVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

}
