package com.realestate.controller;

import com.realestate.dto.ServiceDto;
import com.realestate.exception.ServiceNotFoundException;
import com.realestate.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/add")
    public ResponseEntity<String> createService(@RequestBody ServiceDto serviceDto) {
        try {
            serviceService.addService(serviceDto);
            return ResponseEntity.ok("Service created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create service: " + e.getMessage());
        }
    }

    @PutMapping("/update/{serviceId}")
    public ResponseEntity<String> editService(@PathVariable("serviceId") Integer serviceId, @RequestBody ServiceDto serviceDto) {
        try {
            serviceService.updateService(serviceDto, serviceId);
            return ResponseEntity.ok("Service updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update service: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<ServiceDto>> showAllServices(Pageable pageable) {
        Page<ServiceDto> allServices = serviceService.getAllServices(pageable);
        return new ResponseEntity<>(allServices, HttpStatus.OK);
    }

    @GetMapping("/getById/{serviceId}")
    public ResponseEntity<?> showServiceById(@PathVariable("serviceId") Integer serviceId) {
        try {
            ServiceDto serviceById = serviceService.getServiceById(serviceId);
            return new ResponseEntity<>(serviceById, HttpStatus.OK);
        } catch (ServiceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{serviceId}")
    public ResponseEntity<?> deleteServiceById(@PathVariable("serviceId") Integer serviceId) {
        try {
            serviceService.deleteServiceById(serviceId);
            return ResponseEntity.ok("Service deleted successfully.");
        } catch (ServiceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
