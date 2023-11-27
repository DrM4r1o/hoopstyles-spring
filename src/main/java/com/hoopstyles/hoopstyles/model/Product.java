package com.hoopstyles.hoopstyles.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	
	private float price;
	
	private String image;
	
	@ManyToOne // todo Product tiene un owner
	private UserHoop owner;
	
	@ManyToOne // Un Product podrá estar en una Order
	private BasketballOrder order;
	
	
	public Product() {}

	public Product(String name, float price, String image, UserHoop owner) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String name) {
		this.name = name;
	}

	public float getPrecio() {
		return price;
	}

	public void setPrecio(float price) {
		this.price = price;
	}

	public String getImagen() {
		return image;
	}

	public void setImagen(String image) {
		this.image = image;
	}

	public UserHoop getPropietario() {
		return owner;
	}

	public void setPropietario(UserHoop owner) {
		this.owner = owner;
	}

	public BasketballOrder getOrder() {
		return order;
	}

	public void setOrder(BasketballOrder Order) {
		this.order = Order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, id, image, name, price, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(order, other.order) && id == other.id && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", owner=" + owner + ", Order=" + order + "]";
	}
	
	
	
	
}
