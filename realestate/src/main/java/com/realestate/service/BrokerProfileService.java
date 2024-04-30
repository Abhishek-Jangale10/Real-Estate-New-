package com.realestate.service;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.exception.BrokerProfileNotFoundException;

import java.util.List;

public interface BrokerProfileService {
    void addBrokerProfile(BrokerProfileDto brokerProfileDto);

    void updateBrokerProfile(BrokerProfileDto brokerProfileDto, Integer brokerProfileId);

    List<BrokerProfileDto> getAllBrokerProfiles();

    BrokerProfileDto getBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;

    void deleteBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;
}