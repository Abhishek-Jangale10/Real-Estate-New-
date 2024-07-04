package com.realestate.service;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.exception.ServiceBookingNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceBookingService {
    void addServiceBooking(ServiceBookingDto serviceBookingDto);

    void updateServiceBooking(ServiceBookingDto serviceBookingDto, Integer serviceBookingId);

    Page<ServiceBookingDto> getAllServiceBookings(Pageable pageable); // Updated method signature

    ServiceBookingDto getServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException;

    void deleteServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException;
}
