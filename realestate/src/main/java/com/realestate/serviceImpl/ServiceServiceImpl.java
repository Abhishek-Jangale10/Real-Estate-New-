package com.realestate.serviceImpl;

import com.realestate.dto.ServiceDto;
import com.realestate.entity.Service1;
import com.realestate.exception.ServiceNotFoundException;
import com.realestate.repository.ServiceRepository;
import com.realestate.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void addService(ServiceDto serviceDto) {
        Service1 service = new Service1(serviceDto);
        serviceRepository.save(service);
    }

    @Override
    public void updateService(ServiceDto serviceDto, Integer serviceId) {
        Optional<Service1> optionalService = serviceRepository.findById(serviceId);

        if (optionalService.isPresent()) {
            Service1 service = optionalService.get();
            service.setDescription(serviceDto.getDescription());
            service.setName(serviceDto.getName());
            serviceRepository.save(service);
        } else {
            throw new ServiceNotFoundException("Service not found with ID: " + serviceId);
        }
    }

    @Override
    public Page<ServiceDto> getAllServices(Pageable pageable) {
        Page<Service1> services = serviceRepository.findAll(pageable);
        return services.map(ServiceDto::new);
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) throws ServiceNotFoundException {
        Optional<Service1> optionalService = serviceRepository.findById(serviceId);
        if (optionalService.isPresent()) {
            return new ServiceDto(optionalService.get());
        } else {
            throw new ServiceNotFoundException("Service not found with ID: " + serviceId);
        }
    }

    @Override
    public void deleteServiceById(Integer serviceId) throws ServiceNotFoundException {
        Optional<Service1> optionalService = serviceRepository.findById(serviceId);
        if (optionalService.isPresent()) {
            serviceRepository.deleteById(serviceId);
        } else {
            throw new ServiceNotFoundException("Service not found with ID: " + serviceId);
        }
    }
}
