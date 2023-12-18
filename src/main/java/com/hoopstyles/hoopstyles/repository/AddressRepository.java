package com.hoopstyles.hoopstyles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public Address findByUser(Address a);
    
}

