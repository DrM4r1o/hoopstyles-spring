package com.hoopstyles.hoopstyles.services;

import java.util.List;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository repository;
    
    public Category insert(Category c) {
        return repository.save(c);
    }
    
    public Category searchById(long id) {
        return repository.findById(id).orElse(null);
    }
    
    public List<Category> all() {
        return repository.findAll();
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }
    
    public Category edit(Category c) {
        return repository.save(c);
    }
    
    public void delete(Category c) {
        repository.delete(c);
    }

    public void save(Category category) {
        repository.save(category);
    }

    public long countCategories() {
        return repository.count();
    }
    
}
