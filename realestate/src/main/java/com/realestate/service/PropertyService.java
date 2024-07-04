package com.realestate.service;

import com.realestate.dto.PropertyDto;
import com.realestate.exception.PropertyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {
    void addProperty(PropertyDto propertyDto);

    void updateProperty(PropertyDto propertyDto, Integer propertyId);

    Page<PropertyDto> getAllProperties(Pageable pageable); // Updated method signature

    PropertyDto getPropertyById(Integer propertyId) throws PropertyNotFoundException;

    void deletePropertyById(Integer propertyId) throws PropertyNotFoundException;
}
