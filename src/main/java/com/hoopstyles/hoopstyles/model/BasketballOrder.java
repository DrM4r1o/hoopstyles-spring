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
@EntityListeners(AuditingEntityListener.class)
public class BasketballOrder {

	@Id @GeneratedValue
	private long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOrder;
	
	@ManyToOne
	private UserHoop owner;

    @OneToMany
    private List<OrderLine> orderLines;
	
	private boolean state;
	
	public BasketballOrder() { }

	public BasketballOrder(UserHoop owner, List<OrderLine> orderLines) {
		this.owner = owner;
        this.orderLines = orderLines;
		this.state = true;
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

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLine(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void addOrderLine(OrderLine orderLine) {
		this.orderLines.add(orderLine);
	}

	public void removeOrderLine(OrderLine orderLine) {
		this.orderLines.remove(orderLine);
	}
	
	public boolean isActive() {
		return state;
	}

	public void setState(boolean newState) {
		this.state = newState;
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

