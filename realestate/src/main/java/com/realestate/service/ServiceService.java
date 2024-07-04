package com.realestate.service;

import com.realestate.dto.ServiceDto;
import com.realestate.exception.ServiceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    void addService(ServiceDto serviceDto);

    void updateService(ServiceDto serviceDto, Integer serviceId);

    Page<ServiceDto> getAllServices(Pageable pageable); // Updated method signature

    ServiceDto getServiceById(Integer serviceId) throws ServiceNotFoundException;

    void deleteServiceById(Integer serviceId) throws ServiceNotFoundException;
}
