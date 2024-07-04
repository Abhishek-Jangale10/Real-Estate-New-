package com.realestate.serviceImpl;

import com.realestate.dto.ServiceBookingDto;
import com.realestate.entity.ServiceBooking;
import com.realestate.exception.ServiceBookingNotFoundException;
import com.realestate.repository.ServiceBookingRepository;
import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<ServiceBookingDto> getAllServiceBookings(Pageable pageable) {
        Page<ServiceBooking> serviceBookings = serviceBookingRepository.findAll(pageable);
        return serviceBookings.map(ServiceBookingDto::new);
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
