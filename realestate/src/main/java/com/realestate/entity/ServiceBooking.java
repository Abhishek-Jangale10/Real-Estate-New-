package com.realestate.entity;

import com.realestate.dto.ServiceBookingDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceBookingId;
    private  String status;
    private  String col;
    private String serviceBookingCol;


    @ManyToOne(fetch = FetchType.EAGER)
    private Service1 service;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    public ServiceBooking(ServiceBookingDto serviceBookingDto){
        this.serviceBookingId = serviceBookingDto.getServiceBookingId();
        this.status = serviceBookingDto.getStatus();
        this.col = serviceBookingDto.getCol();
        this.serviceBookingCol = serviceBookingDto.getServiceBookingCol();
    }
}
