package com.realestate.entity;

import com.realestate.dto.BrokerProfileDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brokerProfileId;

    private String name;
    private String docNumber;
    private String fullAddress;
    private String city;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    // Getters and setters


public BrokerProfile(BrokerProfileDto brokerProfileDto){
        this.brokerProfileId = brokerProfileDto.getBrokerProfileId();
        this.name = brokerProfileDto.getName();
        this.docNumber = brokerProfileDto.getDocNumber();
        this.fullAddress = brokerProfileDto.getFullAddress();
        this.city = brokerProfileDto.getCity();
    }

}
