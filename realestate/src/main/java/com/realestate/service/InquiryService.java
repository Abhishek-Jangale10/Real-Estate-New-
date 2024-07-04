package com.realestate.service;

import com.realestate.dto.InquiryDto;
import com.realestate.exception.InquiryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryService {
    void addInquiry(InquiryDto inquiryDto);

    void updateInquiry(InquiryDto inquiryDto, Integer inquiryId);

    Page<InquiryDto> getAllInquiries(Pageable pageable); // Updated method signature

    InquiryDto getInquiryById(Integer inquiryId) throws InquiryNotFoundException;

    void deleteInquiryById(Integer inquiryId) throws InquiryNotFoundException;
}
