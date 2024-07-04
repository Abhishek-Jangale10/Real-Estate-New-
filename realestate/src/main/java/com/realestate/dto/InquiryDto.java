package com.realestate.dto;

import com.realestate.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private Integer inquiryId;

    private String message;
    private String status;

    private Integer propertyId;

    public InquiryDto(Inquiry inquiry){
        this.inquiryId = inquiry.getInquiryId();
        this.message = inquiry.getMessage();
        this.status = inquiry.getStatus();
    }
}
