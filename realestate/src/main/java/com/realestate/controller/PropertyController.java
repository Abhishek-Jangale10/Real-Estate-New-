package com.realestate.controller;

import com.realestate.dto.PropertyDto;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<String> createProperty(@RequestBody PropertyDto propertyDto) {
        try {
            propertyService.addProperty(propertyDto);
            return ResponseEntity.ok("Property created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create property: " + e.getMessage());
        }
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<String> editProperty(@PathVariable("propertyId") Integer propertyId, @RequestBody PropertyDto propertyDto) {
        try {
            propertyService.updateProperty(propertyDto, propertyId);
            return ResponseEntity.ok("Property updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update property: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<PropertyDto>> showAllProperties(Pageable pageable) {
        Page<PropertyDto> allProperties = propertyService.getAllProperties(pageable);
        return new ResponseEntity<>(allProperties, HttpStatus.OK);
    }

    @GetMapping("/getById/{propertyId}")
    public ResponseEntity<?> showPropertyById(@PathVariable("propertyId") Integer propertyId) {
        try {
            PropertyDto propertyById = propertyService.getPropertyById(propertyId);
            return new ResponseEntity<>(propertyById, HttpStatus.OK);
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{propertyId}")
    public ResponseEntity<?> deletePropertyById(@PathVariable("propertyId") Integer propertyId) {
        try {
            propertyService.deletePropertyById(propertyId);
            return ResponseEntity.ok("Property deleted successfully.");
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
