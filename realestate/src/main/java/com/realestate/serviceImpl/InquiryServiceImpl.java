package com.realestate.serviceImpl;

import com.realestate.dto.InquiryDto;
import com.realestate.entity.Inquiry;
import com.realestate.exception.InquiryNotFoundException;
import com.realestate.repository.InquiryRepository;
import com.realestate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public void addInquiry(InquiryDto inquiryDto) {
        Inquiry inquiry = new Inquiry(inquiryDto);
        inquiryRepository.save(inquiry);
    }

    @Override
    public void updateInquiry(InquiryDto inquiryDto, Integer inquiryId) {
        Optional<Inquiry> optionalInquiry = inquiryRepository.findById(inquiryId);

        if (optionalInquiry.isPresent()) {
            Inquiry inquiry = optionalInquiry.get();
            inquiry.setMessage(inquiryDto.getMessage());
            inquiry.setStatus(inquiryDto.getStatus());
            inquiryRepository.save(inquiry);
        } else {
            throw new InquiryNotFoundException("Inquiry not found with ID: " + inquiryId);
        }
    }

    @Override
    public Page<InquiryDto> getAllInquiries(Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findAll(pageable);
        return inquiries.map(InquiryDto::new);
    }

    @Override
    public InquiryDto getInquiryById(Integer inquiryId) throws InquiryNotFoundException {
        Optional<Inquiry> optionalInquiry = inquiryRepository.findById(inquiryId);
        if (optionalInquiry.isPresent()) {
            return new InquiryDto(optionalInquiry.get());
        } else {
            throw new InquiryNotFoundException("Inquiry not found with ID: " + inquiryId);
        }
    }

    @Override
    public void deleteInquiryById(Integer inquiryId) throws InquiryNotFoundException {
        Optional<Inquiry> optionalInquiry = inquiryRepository.findById(inquiryId);
        if (optionalInquiry.isPresent()) {
            inquiryRepository.deleteById(inquiryId);
        } else {
            throw new InquiryNotFoundException("Inquiry not found with ID: " + inquiryId);
        }
    }
}
