package com.realestate.dto;

import java.util.List;

public class ResponsePropertyDto {
    private String message;
    private List<PropertyDto> properties;
    private String exception;
    private int totalPages;

    public ResponsePropertyDto(String message) {
        this.message = message;
    }

    public ResponsePropertyDto(String message, List<PropertyDto> properties, int totalPages) {
        this.message = message;
        this.properties = properties;
        this.totalPages = totalPages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PropertyDto> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDto> properties) {
        this.properties = properties;
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
