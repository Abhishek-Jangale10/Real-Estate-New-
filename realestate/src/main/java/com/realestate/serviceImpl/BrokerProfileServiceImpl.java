package com.realestate.serviceImpl;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.entity.BrokerProfile;
import com.realestate.exception.BrokerProfileNotFoundException;
import com.realestate.repository.BrokerProfileRepository;
import com.realestate.service.BrokerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrokerProfileServiceImpl implements BrokerProfileService {

    @Autowired
    private BrokerProfileRepository brokerProfileRepository;

    @Override
    public void addBrokerProfile(BrokerProfileDto brokerProfileDto) {
        BrokerProfile brokerProfile = new BrokerProfile(brokerProfileDto);
        brokerProfileRepository.save(brokerProfile);
    }

    @Override
    public void updateBrokerProfile(BrokerProfileDto brokerProfileDto, Integer brokerProfileId) {
        Optional<BrokerProfile> optionalBrokerProfile = brokerProfileRepository.findById(brokerProfileId);

        if (optionalBrokerProfile.isPresent()) {
            BrokerProfile brokerProfile = optionalBrokerProfile.get();
            brokerProfile.setName(brokerProfileDto.getName());
            brokerProfile.setDocNumber(brokerProfileDto.getDocNumber());
            brokerProfile.setFullAddress(brokerProfileDto.getFullAddress());
            brokerProfile.setCity(brokerProfileDto.getCity());
            brokerProfileRepository.save(brokerProfile);
        } else {
            throw new BrokerProfileNotFoundException("Broker profile not found with ID: " + brokerProfileId);
        }
    }

    @Override
    public Page<BrokerProfileDto> getAllBrokerProfiles(Pageable pageable) {
        Page<BrokerProfile> brokerProfiles = brokerProfileRepository.findAll(pageable);
        return brokerProfiles.map(BrokerProfileDto::new);
    }

    @Override
    public BrokerProfileDto getBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException {
        Optional<BrokerProfile> optionalBrokerProfile = brokerProfileRepository.findById(brokerProfileId);
        if (optionalBrokerProfile.isPresent()) {
            return new BrokerProfileDto(optionalBrokerProfile.get());
        } else {
            throw new BrokerProfileNotFoundException("Broker profile not found with ID: " + brokerProfileId);
        }
    }

    @Override
    public void deleteBrokerProfileById(Integer brokerProfileId) throws BrokerProfileNotFoundException {
        Optional<BrokerProfile> optionalBrokerProfile = brokerProfileRepository.findById(brokerProfileId);
        if (optionalBrokerProfile.isPresent()) {
            brokerProfileRepository.deleteById(brokerProfileId);
        } else {
            throw new BrokerProfileNotFoundException("Broker profile not found with ID: " + brokerProfileId);
        }
    }
}
