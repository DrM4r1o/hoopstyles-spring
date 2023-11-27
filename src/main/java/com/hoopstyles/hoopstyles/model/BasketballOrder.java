package com.hoopstyles.hoopstyles.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@EntityListeners(AuditingEntityListener.class) // Necesario para el funcionamiento de CreatedDate
public class BasketballOrder {

	@Id @GeneratedValue
	private long id;
	
	@CreatedDate //Genera la fecha a la hora de la inserción en BD
	@Temporal(TemporalType.TIMESTAMP) //Hace que el campo dateOrder se mapee como fecha y hora en BD
	private Date dateOrder;
	
	@ManyToOne
	private UserHoop owner;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLine;
	
	public BasketballOrder() { }

	// El ID y la Fecha se autogeneran
	public BasketballOrder(UserHoop owner, List<OrderLine> orderLine) {
		this.owner = owner;
        this.orderLine = orderLine;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public UserHoop getOwner() {
		return owner;
	}

	public void setOwner(UserHoop owner) {
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
		BasketballOrder other = (BasketballOrder) obj;
		return Objects.equals(dateOrder, other.dateOrder) && id == other.id
				&& Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "BasketballOrder [id=" + id + ", dateOrder=" + dateOrder + ", owner=" + owner + "]";
	}
	
	
}

