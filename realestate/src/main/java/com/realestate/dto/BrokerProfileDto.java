package com.realestate.dto;

import com.realestate.entity.BrokerProfile;
import com.realestate.entity.User;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrokerProfileDto {
    private Integer brokerProfileId;
    private String name;
    private String docNumber;
    private String fullAddress;
    private String city;
    private Integer userId;


    @OneToOne(mappedBy = "brokerProfile")
    private User user;

    public BrokerProfileDto(BrokerProfile brokerProfile){
        this.brokerProfileId = brokerProfile.getBrokerProfileId();
        this.name = brokerProfile.getName();
        this.docNumber = brokerProfile.getDocNumber();
        this.fullAddress = brokerProfile.getFullAddress();
        this.city = brokerProfile.getCity();

    }
}
