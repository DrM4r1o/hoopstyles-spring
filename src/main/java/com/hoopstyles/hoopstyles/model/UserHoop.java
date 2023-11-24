package com.hoopstyles.hoopstyles.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	
	@CreatedDate //Genera la fecha a la hora de la inserción en BD
	@Temporal(TemporalType.TIMESTAMP) //Hace que el campo fechaCompra se mapee como fecha y hora en BD
	private Date dateCreation;
	
	private String email; 
	private String password;
	
	public UserHoop() { }

	public UserHoop(String name, String surname, String profileImg, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.profileImg = profileImg;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String surname) {
		this.surname = surname;
	}

	public String getprofileImg() {
		return profileImg;
	}

	public void setprofileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public Date getdateCreation() {
		return dateCreation;
	}

	public void setdateCreation(Date dateCreation) {
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