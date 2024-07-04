package com.realestate.service;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.exception.BrokerProfileNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrokerProfileService {
    void addBrokerProfile(BrokerProfileDto brokerProfileDto);

    void updateBrokerProfile(BrokerProfileDto brokerProfileDto, Integer brokerProfileId);

    Page<BrokerProfileDto> getAllBrokerProfiles(Pageable pageable); // Updated method signature

    BrokerProfileDto getBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;

    void deleteBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException;
}
