package com.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBrokerProfileDto {
    private String message;
    private List<BrokerProfileDto> brokerProfiles;
    private String exception;
    private int totalPages;

    public ResponseBrokerProfileDto(String message) {
        this.message = message;
    }



    public ResponseBrokerProfileDto(String message, List<BrokerProfileDto> brokerProfiles, int totalPages) {
        this.message = message;
        this.brokerProfiles = brokerProfiles;
        this.totalPages = totalPages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BrokerProfileDto> getBrokerProfiles() {
        return brokerProfiles;
    }

    public void setBrokerProfiles(List<BrokerProfileDto> brokerProfiles) {
        this.brokerProfiles = brokerProfiles;
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
