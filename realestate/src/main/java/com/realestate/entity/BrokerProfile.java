package com.realestate.entity;

import com.realestate.dto.BrokerProfileDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(mappedBy = "brokerProfile")
    private User user;
    public BrokerProfile(BrokerProfileDto brokerProfileDto){
        this.brokerProfileId = brokerProfileDto.getBrokerProfileId();
        this.name = brokerProfileDto.getName();
        this.docNumber = brokerProfileDto.getDocNumber();
        this.fullAddress = brokerProfileDto.getFullAddress();
        this.city = brokerProfileDto.getCity();
    }

}
