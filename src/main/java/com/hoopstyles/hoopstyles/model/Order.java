package com.hoopstyles.hoopstyles.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@EntityListeners(AuditingEntityListener.class) // Necesario para el funcionamiento de CreatedDate
public class Order {

	@Id @GeneratedValue
	private long id;
	
	@CreatedDate //Genera la fecha a la hora de la inserción en BD
	@Temporal(TemporalType.TIMESTAMP) //Hace que el campo dateOrder se mapee como fecha y hora en BD
	private Date dateOrder;
	
	@ManyToOne
	private UserHoop owner;
	
	public Order() { }

	// El ID y la Fecha se autogeneran
	public Order(UserHoop owner) {
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaOrder() {
		return dateOrder;
	}

	public void setFechaOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public UserHoop getPropietario() {
		return owner;
	}

	public void setPropietario(UserHoop owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOrder, id, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(dateOrder, other.dateOrder) && id == other.id
				&& Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", dateOrder=" + dateOrder + ", owner=" + owner + "]";
	}
	
	
}

