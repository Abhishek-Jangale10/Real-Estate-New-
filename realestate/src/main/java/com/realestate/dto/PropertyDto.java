package com.realestate.dto;

import com.realestate.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    private Integer propertyId;

    private String location;
    private Long price;
    private String title;
    private String type;

    public PropertyDto(Property property){
        this.propertyId = property.getPropertyId();
        this.location = property.getLocation();
        this.price = property.getPrice();
        this.title = property.getTitle();
        this.type = property.getType();    }
}
