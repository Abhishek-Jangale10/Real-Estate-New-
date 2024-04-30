package com.realestate.repository;

import com.realestate.entity.BrokerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerProfileRepository extends JpaRepository<BrokerProfile, Integer> {
}

