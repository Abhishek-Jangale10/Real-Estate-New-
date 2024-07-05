package com.realestate.service;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.exception.PageNotFoundException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.ServiceBookingNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceBookingService {
    void addServiceBooking(ServiceBookingDto serviceBookingDto);

    void updateServiceBooking(ServiceBookingDto serviceBookingDto, Integer serviceBookingId);


    List<ServiceBookingDto> getAllServiceBookings(int pageNo, int pageSize) throws PageNotFoundException, ResourceNotFoundException;

    ServiceBookingDto getServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException;

    void deleteServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException;
}
