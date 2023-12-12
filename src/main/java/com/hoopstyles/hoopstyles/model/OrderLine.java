package com.hoopstyles.hoopstyles.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderLine {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private Product product;
    
    @ManyToOne
    private BasketballOrder order;
    
    private int quantity;

    public OrderLine() { }

    public OrderLine(BasketballOrder order) { 
        this.order = order;
    }

    public OrderLine(int quantity, Product product, BasketballOrder order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BasketballOrder getOrder() {
        return order;
    }

    public void setOrder(BasketballOrder order) {
        this.order = order;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine )) return false;
        return id != 0 && id == (((OrderLine) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, id);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                "order=" + order.getId() +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
