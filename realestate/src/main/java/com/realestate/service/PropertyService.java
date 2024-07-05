package com.realestate.service;

import com.realestate.dto.PropertyDto;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {
    void addProperty(PropertyDto propertyDto);

    void updateProperty(PropertyDto propertyDto, Integer propertyId);

    Page<PropertyDto> getAllProperties(int pageNo, int pageSize) throws PageNotFoundException;

    PropertyDto getPropertyById(Integer propertyId) throws PropertyNotFoundException;

    void deletePropertyById(Integer propertyId) throws PropertyNotFoundException;
}
