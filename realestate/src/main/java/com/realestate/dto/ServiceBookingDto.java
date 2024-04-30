package com.realestate.dto;

import com.realestate.entity.ServiceBooking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBookingDto {
    private Integer serviceBookingId;
    private  String status;
    private  String col;
    private String serviceBookingCol;

    public ServiceBookingDto(ServiceBooking serviceBooking){
        this.serviceBookingId = serviceBooking.getServiceBookingId();
        this.status = serviceBooking.getStatus();
        this.col = serviceBooking.getCol();
        this.serviceBookingCol = serviceBooking.getServiceBookingCol();
    }
}
