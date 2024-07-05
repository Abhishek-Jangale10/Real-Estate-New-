package com.realestate.serviceImpl;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.entity.ServiceBooking;
import com.realestate.exception.PageNotFoundException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.ServiceBookingNotFoundException;
import com.realestate.repository.ServiceBookingRepository;
import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceBookingServiceImpl implements ServiceBookingService {

    @Autowired
    private ServiceBookingRepository serviceBookingRepository;

    @Override
    public void addServiceBooking(ServiceBookingDto serviceBookingDto) {
        ServiceBooking serviceBooking = new ServiceBooking(serviceBookingDto);
        serviceBookingRepository.save(serviceBooking);
    }

    @Override
    public void updateServiceBooking(ServiceBookingDto serviceBookingDto, Integer serviceBookingId) {
        Optional<ServiceBooking> optionalServiceBooking = serviceBookingRepository.findById(serviceBookingId);

        if (optionalServiceBooking.isPresent()) {
            ServiceBooking serviceBooking = optionalServiceBooking.get();
            serviceBooking.setStatus(serviceBookingDto.getStatus());
            serviceBooking.setCol(serviceBookingDto.getCol());
            serviceBooking.setServiceBookingCol(serviceBookingDto.getServiceBookingCol());
            serviceBookingRepository.save(serviceBooking);
        } else {
            throw new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId);
        }
    }

    @Override
    public List<ServiceBookingDto> getAllServiceBookings(int pageNo, int pageSize) throws PageNotFoundException, ResourceNotFoundException {
        // Retrieve all service bookings from repository
        List<ServiceBooking> serviceBookings = serviceBookingRepository.findAll();

        // Calculate total number of service bookings
        int totalServiceBookings = serviceBookings.size();
        int totalPages = (int) Math.ceil((double) totalServiceBookings / pageSize);

        // Throw exception if no service bookings found
        if (serviceBookings.isEmpty()) {
            throw new ResourceNotFoundException("Service bookings not found");
        }

        // Throw exception if page number is out of range
        if (pageNo < 0 || pageNo >= totalPages) {
            throw new PageNotFoundException("Page not found");
        }

        // Calculate indices for pagination
        int pageStart = pageNo * pageSize;
        int pageEnd = Math.min(pageStart + pageSize, totalServiceBookings);

        // Prepare list to hold paginated ServiceBookingDto objects
        List<ServiceBookingDto> paginatedServiceBookings = new ArrayList<>();

        // Convert ServiceBooking entities to ServiceBookingDto objects
        for (int i = pageStart; i < pageEnd; i++) {
            ServiceBooking serviceBooking = serviceBookings.get(i);
            ServiceBookingDto serviceBookingDto = new ServiceBookingDto(serviceBooking);
            paginatedServiceBookings.add(serviceBookingDto);
        }

        return paginatedServiceBookings;
    }


    @Override
    public ServiceBookingDto getServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException {
        Optional<ServiceBooking> optionalServiceBooking = serviceBookingRepository.findById(serviceBookingId);
        if (optionalServiceBooking.isPresent()) {
            return new ServiceBookingDto(optionalServiceBooking.get());
        } else {
            throw new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId);
        }
    }

    @Override
    public void deleteServiceBookingById(Integer serviceBookingId) throws ServiceBookingNotFoundException {
        Optional<ServiceBooking> optionalServiceBooking = serviceBookingRepository.findById(serviceBookingId);
        if (optionalServiceBooking.isPresent()) {
            serviceBookingRepository.deleteById(serviceBookingId);
        } else {
            throw new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId);
        }
    }
}
