package com.realestate.dto;

import java.util.List;

public class ResponseServiceBookingDto {

    private String message;
    private List<ServiceBookingDto> serviceBookings;
    private String exception;
    private int totalPages;

    public ResponseServiceBookingDto(String message) {
        this.message = message;
    }

    public ResponseServiceBookingDto(String message, List<ServiceBookingDto> serviceBookings, int totalPages) {
        this.message = message;
        this.serviceBookings = serviceBookings;
        this.totalPages = totalPages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ServiceBookingDto> getServiceBookings() {
        return serviceBookings;
    }

    public void setServiceBookings(List<ServiceBookingDto> serviceBookings) {
        this.serviceBookings = serviceBookings;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
