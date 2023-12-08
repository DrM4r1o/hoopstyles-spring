package com.hoopstyles.hoopstyles.model;

public class InfoProfileUpdated {

    private String name;
    private String surname;
    private String email;
    private String newPassword;

    public InfoProfileUpdated() {}

    public InfoProfileUpdated(String name, String surname, String email, String newPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InfoProfileUpdated name(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public InfoProfileUpdated surname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InfoProfileUpdated email(String email) {
        this.email = email;
        return this;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public InfoProfileUpdated newPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", email='" + getEmail() + "'" +
            ", newPassword='" + getNewPassword() + "'" +
            "}";
    }
    
}