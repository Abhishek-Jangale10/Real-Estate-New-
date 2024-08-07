package com.realestate.dto;

import com.realestate.entity.Service1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private Integer serviceId;
    private String description;
    private String name;

    public ServiceDto(Service1 service){
        this.serviceId = service.getServiceId();
        this.description = service.getDescription();
        this.name = service.getName();
    }
}
