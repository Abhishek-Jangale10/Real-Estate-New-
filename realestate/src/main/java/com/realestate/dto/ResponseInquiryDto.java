package com.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInquiryDto {
    private String message;
    private List<InquiryDto> inquiries;
    private String exception;
    private int totalPages;

    public ResponseInquiryDto(String message) {
        this.message = message;
    }

    public ResponseInquiryDto(String message, List<InquiryDto> inquiries, int totalPages) {
        this.message = message;
        this.inquiries = inquiries;
        this.totalPages = totalPages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InquiryDto> getInquiries() {
        return inquiries;
    }

    public void setInquiries(List<InquiryDto> inquiries) {
        this.inquiries = inquiries;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
