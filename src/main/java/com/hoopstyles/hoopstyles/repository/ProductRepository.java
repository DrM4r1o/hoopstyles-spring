package com.hoopstyles.hoopstyles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
    List<Product> findByCategoriesIn(List<Category> categories);

    List<Product> findByPriceBetween(int min, int max); 

    List<Product> findByPriceBetweenAndCategoriesIn(int min, int max, List<Category> categories);
	
	// Buscar productos por cadena de caracteres y propietario
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, UserHoop owner);

    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByNameContainsIgnoreCaseAndCategoriesIn(String name, List<Category> categories);

}
