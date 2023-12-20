package com.hoopstyles.hoopstyles.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

	@Id @GeneratedValue
	private long id;
	
	private String name;	
    
    private String description;

	private float price;
	
	private String image;

    @ManyToMany
    private List<Category> categories;
	
	@ManyToOne
	private UserHoop owner;
	
	public Product() {}

	public Product(String name, float price, String image, UserHoop owner, String description, List<Category> categories) {
        ProductBase(name, price);
		this.image = image;
		this.owner = owner;
        this.description = description;
        this.categories = categories;
	}

    public Product(String name, float price, String image, String description, List<Category> categories) {
        ProductBase(name, price);
        this.image = image;
        this.description = description;
        this.categories = categories;
	}

    public Product(String name, float price, String image, String description) {
        ProductBase(name, price);
        this.image = image;
        this.description = description;
	}

    public Product(String name, float price, UserHoop owner, List<Category> categories) {
        ProductBase(name, price);
        this.owner = owner;
        this.categories = categories;
	}

    public Product(String name, float price, List<Category> categories) {
        ProductBase(name, price);
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getCategoriesString() {
        String categoriesString = "";
        for(Category category : categories) {
            categoriesString += category.getName() + ",";
        }
        return categoriesString.substring(0, categoriesString.length() - 1);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, image, name, price, owner);
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
		return id == other.id && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", owner=" + owner + "]";
	}
	
	
	
	
}
