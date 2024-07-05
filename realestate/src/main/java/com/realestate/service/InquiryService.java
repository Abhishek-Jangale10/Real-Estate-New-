package com.realestate.service;

import com.realestate.dto.InquiryDto;
import com.realestate.exception.InquiryNotFoundException;
import com.realestate.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryService {
    void addInquiry(InquiryDto inquiryDto);

    void updateInquiry(InquiryDto inquiryDto, Integer inquiryId);

    Page<InquiryDto> getAllInquiries(int pageNo, int pageSize) throws PageNotFoundException;

    InquiryDto getInquiryById(Integer inquiryId) throws InquiryNotFoundException;

    void deleteInquiryById(Integer inquiryId) throws InquiryNotFoundException;
}
