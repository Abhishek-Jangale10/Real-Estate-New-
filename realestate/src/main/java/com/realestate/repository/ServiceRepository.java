package com.realestate.repository;

import com.realestate.entity.Service1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service1, Integer> {
}
