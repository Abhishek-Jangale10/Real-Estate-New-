package com.realestate.controller;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.exception.BrokerProfileNotFoundException;
import com.realestate.service.BrokerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broker-profile")
public class BrokerProfileController {

    @Autowired
    private BrokerProfileService brokerProfileService;

    @PostMapping("/add")
    public ResponseEntity<String> createBrokerProfile(@RequestBody BrokerProfileDto brokerProfileDto) {
        try {
            this.brokerProfileService.addBrokerProfile(brokerProfileDto);
            return ResponseEntity.ok("Broker profile created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create broker profile: " + e.getMessage());
        }
    }

    @PutMapping("/update/{brokerProfileId}") // Corrected the path variable name to match the method parameter name
    public ResponseEntity<String> editBrokerProfile(@PathVariable("brokerProfileId") Integer brokerProfileId, @RequestBody BrokerProfileDto brokerProfileDto) {
        try {
            this.brokerProfileService.updateBrokerProfile(brokerProfileDto, brokerProfileId);
            return ResponseEntity.ok("Broker profile updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update broker profile: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BrokerProfileDto>> showAllBrokerProfiles() {
        List<BrokerProfileDto> allBrokerProfiles = this.brokerProfileService.getAllBrokerProfiles();
        return new ResponseEntity<>(allBrokerProfiles, HttpStatus.OK);
    }

    @GetMapping("/getById/{brokerProfileId}")
    public ResponseEntity<?> showBrokerProfileById(@PathVariable("brokerProfileId") int brokerProfileId) throws BrokerProfileNotFoundException {
        try {
            BrokerProfileDto brokerProfileById = this.brokerProfileService.getBrokerProfileById(brokerProfileId);
            return new ResponseEntity<>(brokerProfileById, HttpStatus.OK);
        } catch (BrokerProfileNotFoundException e) {
            throw new BrokerProfileNotFoundException("Broker profile not found with ID: " + brokerProfileId);
        }
    }

    @DeleteMapping("/deleteById/{brokerProfileId}")
    public ResponseEntity<?> deleteBrokerProfileById(@PathVariable("brokerProfileId") Integer brokerProfileId) throws BrokerProfileNotFoundException {
        try {
            this.brokerProfileService.deleteBrokerProfileById(brokerProfileId);
            return ResponseEntity.ok("Broker profile deleted successfully.");
        } catch (BrokerProfileNotFoundException e) {
            throw new BrokerProfileNotFoundException("Broker profile not found with ID: " + brokerProfileId);
        }
    }
}
