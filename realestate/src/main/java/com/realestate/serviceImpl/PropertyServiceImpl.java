package com.realestate.serviceImpl;

import com.realestate.dto.PropertyDto;
import com.realestate.entity.Property;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.exception.PageNotFoundException;
import com.realestate.repository.PropertyRepository;
import com.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public void addProperty(PropertyDto propertyDto) {
        Property property = new Property(propertyDto);
        propertyRepository.save(property);
    }

    @Override
    public void updateProperty(PropertyDto propertyDto, Integer propertyId) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);

        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            property.setLocation(propertyDto.getLocation());
            property.setPrice(propertyDto.getPrice());
            property.setTitle(propertyDto.getTitle());
            property.setType(propertyDto.getType());
            propertyRepository.save(property);
        } else {
            throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
        }
    }

    @Override
    public Page<PropertyDto> getAllProperties(int pageNo, int pageSize) throws PageNotFoundException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Property> properties = propertyRepository.findAll(pageable);

        if (properties.isEmpty()) {
            throw new PageNotFoundException("Properties not found");
        }

        return properties.map(PropertyDto::new);
    }

    @Override
    public PropertyDto getPropertyById(Integer propertyId) throws PropertyNotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            return new PropertyDto(optionalProperty.get());
        } else {
            throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
        }
    }

    @Override
    public void deletePropertyById(Integer propertyId) throws PropertyNotFoundException {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            propertyRepository.deleteById(propertyId);
        } else {
            throw new PropertyNotFoundException("Property not found with ID: " + propertyId);
        }
    }
}
