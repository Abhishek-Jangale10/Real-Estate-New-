package com.realestate.serviceImpl;

import com.realestate.dto.BrokerProfileDto;
import com.realestate.entity.BrokerProfile;
import com.realestate.entity.User;
import com.realestate.exception.BrokerProfileNotFoundException;
import com.realestate.exception.PageNotFoundException;
import com.realestate.repository.BrokerProfileRepository;
import com.realestate.repository.UserRepository;
import com.realestate.service.BrokerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class BrokerProfileServiceImpl implements BrokerProfileService {

    @Autowired
    private BrokerProfileRepository brokerProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addBrokerProfile(BrokerProfileDto brokerProfileDto) {
        BrokerProfile brokerProfile = new BrokerProfile(brokerProfileDto);

        Optional<User> userOptional = userRepository.findById(brokerProfileDto.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            brokerProfile.setUser(user);

            if (user.getBrokerProfiles() == null) {
                user.setBrokerProfiles(new HashSet<>());
            }
            user.getBrokerProfiles().add(brokerProfile);

            brokerProfileRepository.save(brokerProfile);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + brokerProfileDto.getUserId());
        }
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
    public List<BrokerProfileDto> getAllBrokerProfiles(int pageNo, int pageSize) throws PageNotFoundException {
        List<BrokerProfile> brokerProfiles = brokerProfileRepository.findAll();
        if (brokerProfiles.isEmpty()) {
            throw new PageNotFoundException("Broker profiles not found");
        }

        int totalBrokerProfiles = brokerProfiles.size();
        int totalPages = (int) Math.ceil((double) totalBrokerProfiles / pageSize);
        if (pageNo < 0 || pageNo >= totalPages) {
            throw new PageNotFoundException("Page not found");
        }

        int startIndex = pageNo * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalBrokerProfiles);
        List<BrokerProfileDto> brokerProfileDtos = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            brokerProfileDtos.add(new BrokerProfileDto(brokerProfiles.get(i)));
        }

        return brokerProfileDtos;
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalBrokerProfiles = (int) brokerProfileRepository.count();
        return (int) Math.ceil((double) totalBrokerProfiles / pageSize);
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
