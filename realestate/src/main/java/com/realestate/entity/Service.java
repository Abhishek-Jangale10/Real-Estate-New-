package com.realestate.entity;

import com.realestate.dto.ServiceDto;
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
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer serviceId;
    private String description;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
    private Set<ServiceBooking> inquiries  = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    public Service(ServiceDto serviceDto){
        this.serviceId = serviceDto.getServiceId();
        this.description = serviceDto.getDescription();
        this.name = serviceDto.getName();
    }
}
