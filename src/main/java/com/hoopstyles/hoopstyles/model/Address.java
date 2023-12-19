package com.hoopstyles.hoopstyles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserHoop user;

    private String name;
    private String street;
    private String city;
    private String cp;

    public Address() { }

    public Address(String street, String city, String cp, String name) {
        this.street = street;
        this.city = city;
        this.cp = cp;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public UserHoop getUser() {
        return user;
    }

    public void setUser(UserHoop user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", city=" + city + ", cp=" + cp + "]";
    }
}
