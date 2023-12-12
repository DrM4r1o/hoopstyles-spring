package com.hoopstyles.hoopstyles.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import jakarta.persistence.criteria.Order;

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

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
	
	private boolean state;
	
	public BasketballOrder() { }

	public BasketballOrder(UserHoop owner, List<OrderLine> orderLines) {
		this.owner = owner;
        this.orderLines = orderLines;
		this.state = true;
	}

    public BasketballOrder(UserHoop owner) {
        this.owner = owner;
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

    public boolean alreadyAdded(OrderLine orderLine) {
        return this.orderLines.contains(orderLine);
    }

	public void setOrderLine(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void addOrderLine(OrderLine orderLine) {
        if(this.orderLines == null)
        {
            this.orderLines = List.of(orderLine);
        }
        else
        {
            this.orderLines.add(orderLine);
        }
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

    public double getTotal() {
        double total = 0;
        for(OrderLine orderLine : this.orderLines) {
            total += orderLine.getSubtotal();
        }

        total = Double.parseDouble(
            String.format("%.2f", total)
            .replace(",", ".")
        );
        return total;
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
        return "BasketballOrder{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                ", owner=" + owner +
                ", orderLines=" + orderLines +
                ", state=" + state +
                '}';
    }
	
}

