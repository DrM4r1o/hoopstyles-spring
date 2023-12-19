package com.hoopstyles.hoopstyles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.Address;
import com.hoopstyles.hoopstyles.repository.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    AddressRepository repository;

    public List<Address> all() {
        return repository.findAll();
    }

    public Address insert(Address a) {
        return repository.save(a);
    }

    public Address searchById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Address findByUser(Address a) {
        return repository.findByUser(a);
    }

    public Address findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(Address a) {
        repository.delete(a);
    }

}