package com.realestate.controller;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.exception.BrokerProfileNotFoundException;
import com.realestate.service.BrokerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broker-profiles")
public class BrokerProfileController {

    @Autowired
    private BrokerProfileService brokerProfileService;

    @PostMapping("/add")
    public ResponseEntity<String> createBrokerProfile(@RequestBody BrokerProfileDto brokerProfileDto) {
        try {
            brokerProfileService.addBrokerProfile(brokerProfileDto);
            return ResponseEntity.ok("Broker profile created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create broker profile: " + e.getMessage());
        }
    }

    @PutMapping("/update/{brokerProfileId}")
    public ResponseEntity<String> editBrokerProfile(@PathVariable("brokerProfileId") Integer brokerProfileId, @RequestBody BrokerProfileDto brokerProfileDto) {
        try {
            brokerProfileService.updateBrokerProfile(brokerProfileDto, brokerProfileId);
            return ResponseEntity.ok("Broker profile updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update broker profile: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<BrokerProfileDto>> showAllBrokerProfiles(Pageable pageable) {
        Page<BrokerProfileDto> allBrokerProfiles = brokerProfileService.getAllBrokerProfiles(pageable);
        return new ResponseEntity<>(allBrokerProfiles, HttpStatus.OK);
    }

    @GetMapping("/getById/{brokerProfileId}")
    public ResponseEntity<?> showBrokerProfileById(@PathVariable("brokerProfileId") Integer brokerProfileId) {
        try {
            BrokerProfileDto brokerProfileById = brokerProfileService.getBrokerProfileById(brokerProfileId);
            return new ResponseEntity<>(brokerProfileById, HttpStatus.OK);
        } catch (BrokerProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{brokerProfileId}")
    public ResponseEntity<?> deleteBrokerProfileById(@PathVariable("brokerProfileId") Integer brokerProfileId) {
        try {
            brokerProfileService.deleteBrokerProfileById(brokerProfileId);
            return ResponseEntity.ok("Broker profile deleted successfully.");
        } catch (BrokerProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
