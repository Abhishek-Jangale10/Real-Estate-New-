package com.realestate.entity;

import com.realestate.dto.InquiryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer inquiryId;

    private String message;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Property property;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public Inquiry(InquiryDto inquiryDto){
        this.inquiryId = inquiryDto.getInquiryId();
        this.message = inquiryDto.getMessage();
        this.status = inquiryDto.getStatus();
    }

}
