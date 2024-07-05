package com.realestate.service;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.exception.BrokerProfileNotFoundException;
import com.realestate.exception.PageNotFoundException;

import java.util.List;

public interface BrokerProfileService {
    void addBrokerProfile(BrokerProfileDto brokerProfileDto);

    void updateBrokerProfile(BrokerProfileDto brokerProfileDto, Integer brokerProfileId);

    List<BrokerProfileDto> getAllBrokerProfiles(int pageNo, int pageSize) throws PageNotFoundException;

    int getTotalPages(int pageSize);

    BrokerProfileDto getBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;

    void deleteBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;
}
