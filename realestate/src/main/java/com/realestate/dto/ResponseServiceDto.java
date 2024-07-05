package com.realestate.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseServiceDto {
    private String message;
    private List<ServiceDto> list;
    private String exception;
    private int totalPages;

    public ResponseServiceDto(String message) {
        this.message = message;
    }

    public ResponseServiceDto(String message, List<ServiceDto> list, int totalPages) {
        this.message = message;
        this.list = list;
        this.totalPages = totalPages;
    }
}
