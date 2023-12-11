package com.hoopstyles.hoopstyles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {  
    Category findByName(String name);
}

