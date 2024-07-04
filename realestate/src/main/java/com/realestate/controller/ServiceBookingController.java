package com.realestate.controller;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.exception.ServiceBookingNotFoundException;
import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-bookings")
public class ServiceBookingController {

    @Autowired
    private ServiceBookingService serviceBookingService;

    @PostMapping("/add")
    public ResponseEntity<String> createServiceBooking(@RequestBody ServiceBookingDto serviceBookingDto) {
        try {
            serviceBookingService.addServiceBooking(serviceBookingDto);
            return ResponseEntity.ok("Service booking created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create service booking: " + e.getMessage());
        }
    }

    @PutMapping("/update/{serviceBookingId}")
    public ResponseEntity<String> editServiceBooking(@PathVariable("serviceBookingId") Integer serviceBookingId, @RequestBody ServiceBookingDto serviceBookingDto) {
        try {
            serviceBookingService.updateServiceBooking(serviceBookingDto, serviceBookingId);
            return ResponseEntity.ok("Service booking updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update service booking: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<ServiceBookingDto>> showAllServiceBookings(Pageable pageable) {
        Page<ServiceBookingDto> allServiceBookings = serviceBookingService.getAllServiceBookings(pageable);
        return new ResponseEntity<>(allServiceBookings, HttpStatus.OK);
    }

    @GetMapping("/getById/{serviceBookingId}")
    public ResponseEntity<?> showServiceBookingById(@PathVariable("serviceBookingId") Integer serviceBookingId) {
        try {
            ServiceBookingDto serviceBookingById = serviceBookingService.getServiceBookingById(serviceBookingId);
            return new ResponseEntity<>(serviceBookingById, HttpStatus.OK);
        } catch (ServiceBookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{serviceBookingId}")
    public ResponseEntity<?> deleteServiceBookingById(@PathVariable("serviceBookingId") Integer serviceBookingId) {
        try {
            serviceBookingService.deleteServiceBookingById(serviceBookingId);
            return ResponseEntity.ok("Service booking deleted successfully.");
        } catch (ServiceBookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
