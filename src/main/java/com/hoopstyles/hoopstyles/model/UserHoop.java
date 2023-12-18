package com.hoopstyles.hoopstyles.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@EntityListeners(AuditingEntityListener.class) // Necesario para el funcionamiento de CreatedDate
public class UserHoop {
	
	@Id @GeneratedValue
	private long id;
	
	private String name;
	private String surname;
	private String profileImg;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
	
	@CreatedDate //Genera la fecha a la hora de la inserción en BD
	@Temporal(TemporalType.TIMESTAMP) //Hace que el campo fechaCompra se mapee como fecha y hora en BD
	private Date dateCreation;
	
	private String email;

    @Column @JsonProperty
	private String password;
	
	public UserHoop() { }

	public UserHoop(String name, String surname, String profileImg, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.profileImg = profileImg;
		this.email = email;
		this.password = password;
        this.role = "USER";
	}

    public UserHoop(String name, String email, String password) {
        this.name = name;
		this.email = email;
		this.password = password;
        this.role = "USER";
    }

    public UserHoop(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
		this.password = password;
        this.role = role;
	}

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses.clear();
        this.addresses.addAll(addresses);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
	}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(surname, profileImg, email, dateCreation, id, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHoop other = (UserHoop) obj;
		return Objects.equals(surname, other.surname) && Objects.equals(profileImg, other.profileImg)
				&& Objects.equals(email, other.email) && Objects.equals(dateCreation, other.dateCreation) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", profileImg=" + profileImg
				+ ", dateCreation=" + dateCreation + ", email=" + email + ", password=" + password + "]";
	}
	
	

}