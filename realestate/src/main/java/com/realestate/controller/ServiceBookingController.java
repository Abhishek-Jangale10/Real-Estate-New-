package com.realestate.controller;

import com.realestate.dto.ResponseServiceBookingDto;
import com.realestate.dto.ServiceBookingDto;
import com.realestate.exception.PageNotFoundException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.ServiceBookingNotFoundException;
import com.realestate.repository.ServiceBookingRepository;
import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-bookings")
public class ServiceBookingController {

    @Autowired
    private ServiceBookingRepository serviceBookingRepository;
    @Autowired
    private ServiceBookingService serviceBookingService;

    @PostMapping("/add")
    public ResponseEntity<String> createServiceBooking(@RequestBody ServiceBookingDto serviceBookingDto) {
        // Implement logic to create service booking
        return ResponseEntity.ok("Service booking created successfully.");
    }

    @PutMapping("/update/{serviceBookingId}")
    public ResponseEntity<String> updateServiceBooking(@PathVariable("serviceBookingId") Integer serviceBookingId,
                                                       @RequestBody ServiceBookingDto serviceBookingDto) {
        // Implement logic to update service booking
        return ResponseEntity.ok("Service booking updated successfully.");
    }

    @DeleteMapping("/delete/{serviceBookingId}")
    public ResponseEntity<String> deleteServiceBooking(@PathVariable("serviceBookingId") Integer serviceBookingId) {
        // Implement logic to delete service booking
        return ResponseEntity.ok("Service booking deleted successfully.");
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseServiceBookingDto> getAllServiceBookings(@RequestParam(defaultValue = "0") int pageNo,
                                                                           @RequestParam(defaultValue = "10") int pageSize) {
        try {
            List<ServiceBookingDto> listOfServiceBookings = (List<ServiceBookingDto>) serviceBookingService.getAllServiceBookings(pageNo, pageSize);
            int totalPages = getTotalPagesForServiceBookings(pageSize); // Calculate total pages based on total service bookings

            ResponseServiceBookingDto response = new ResponseServiceBookingDto("Success", listOfServiceBookings, totalPages);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (PageNotFoundException pageNotFoundException) {
            ResponseServiceBookingDto response = new ResponseServiceBookingDto("Unsuccessful");
            response.setException("Page not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseServiceBookingDto response = new ResponseServiceBookingDto("Error");
            response.setException(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int getTotalPagesForServiceBookings(int pageSize) {
        int totalServiceBookings = serviceBookingRepository.findAll().size();
        return (int) Math.ceil((double) totalServiceBookings / pageSize);
    }


    @GetMapping("/{serviceBookingId}")
    public ResponseEntity<?> getServiceBookingById(@PathVariable("serviceBookingId") Integer serviceBookingId) {
        try {
            ServiceBookingDto serviceBookingDto = serviceBookingService.getServiceBookingById(serviceBookingId);
            return ResponseEntity.status(HttpStatus.OK).body(serviceBookingDto);
        } catch (ServiceBookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
