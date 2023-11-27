package com.hoopstyles.hoopstyles.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Category {

    @Id @GeneratedValue
    private long id;

    private String name;

    private String description;

    @ManyToMany
    private List<Product> products;

    public Category() { }

    public Category(String name, String description, List<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
            Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }

}