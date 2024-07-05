package com.realestate.controller;

import com.realestate.dto.InquiryDto;
import com.realestate.dto.ResponseInquiryDto;
import com.realestate.exception.InquiryNotFoundException;
import com.realestate.exception.PageNotFoundException;
import com.realestate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/add")
    public ResponseEntity<String> createInquiry(@RequestBody InquiryDto inquiryDto) {
        try {
            inquiryService.addInquiry(inquiryDto);
            return ResponseEntity.ok("Inquiry created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create inquiry: " + e.getMessage());
        }
    }

    @PutMapping("/update/{inquiryId}")
    public ResponseEntity<String> editInquiry(@PathVariable("inquiryId") Integer inquiryId, @RequestBody InquiryDto inquiryDto) {
        try {
            inquiryService.updateInquiry(inquiryDto, inquiryId);
            return ResponseEntity.ok("Inquiry updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update inquiry: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseInquiryDto> showAllInquiries(@RequestParam int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            Page<InquiryDto> allInquiries = inquiryService.getAllInquiries(pageNo, pageSize);
            ResponseInquiryDto response = new ResponseInquiryDto("success", allInquiries.getContent(), allInquiries.getTotalPages());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (PageNotFoundException e) {
            ResponseInquiryDto response = new ResponseInquiryDto("unsuccess");
            response.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/getById/{inquiryId}")
    public ResponseEntity<?> showInquiryById(@PathVariable("inquiryId") Integer inquiryId) {
        try {
            InquiryDto inquiryById = inquiryService.getInquiryById(inquiryId);
            return new ResponseEntity<>(inquiryById, HttpStatus.OK);
        } catch (InquiryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{inquiryId}")
    public ResponseEntity<?> deleteInquiryById(@PathVariable("inquiryId") Integer inquiryId) {
        try {
            inquiryService.deleteInquiryById(inquiryId);
            return ResponseEntity.ok("Inquiry deleted successfully.");
        } catch (InquiryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
