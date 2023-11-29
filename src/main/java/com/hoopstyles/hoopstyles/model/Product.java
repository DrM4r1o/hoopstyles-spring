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
    
    private String description;

	private float price;
	
	private String image;
	
	@ManyToOne // todo Product tiene un owner
	private UserHoop owner;
	
	@ManyToOne // Un Product podrá estar en una Order
	private BasketballOrder order;
	
	
	public Product() {}

	public Product(String name, float price, String image, UserHoop owner, String description) {
        ProductBase(name, price);
		this.image = image;
		this.owner = owner;
        this.description = description;
	}

    public Product(String name, float price, String image, String description) {
        ProductBase(name, price);
        this.image = image;
        this.description = description;
	}

    public Product(String name, float price, UserHoop owner) {
        ProductBase(name, price);
        this.owner = owner;
	}

    public Product(String name, float price) {
        ProductBase(name, price);
	}

    private void ProductBase(String name, float price) {
        this.name = name;
        this.price = price;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserHoop getOwner() {
		return owner;
	}

	public void setOwner(UserHoop owner) {
		this.owner = owner;
	}

	public BasketballOrder getOrder() {
		return order;
	}

	public void setOrder(BasketballOrder Order) {
		this.order = Order;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
