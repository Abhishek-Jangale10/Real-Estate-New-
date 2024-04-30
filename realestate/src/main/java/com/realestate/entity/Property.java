package com.realestate.entity;

import com.realestate.dto.PropertyDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer propertyId;
    private String location;
    private Long price;
    private String title;
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "property")
    private Set<Inquiry> inquiries  = new HashSet<>();


    public Property(PropertyDto propertyDto){
        this.propertyId = propertyDto.getPropertyId();
        this.location = propertyDto.getLocation();
        this.price = propertyDto.getPrice();
        this.title = propertyDto.getTitle();
        this.type = propertyDto.getType();
    }

}
