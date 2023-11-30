package com.hoopstyles.hoopstyles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoopstyles.hoopstyles.model.BasketballOrder;
import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;

public interface ProductRepository extends JpaRepository<Product, Long> {

	// Buscar todos los productos de un usuario
	List<Product> findByOwner(UserHoop owner);
	
	//Buscar todos los productos de una compra
	List<Product> findByOrder(BasketballOrder compra);

    List<Product> findByCategoriesIn(List<Category> categories);
	
	//Buscar todos los productos donde la compra sea nula (que no se hayan vendido)
	List<Product> findByOrderIsNull();

    List<Product> findByPriceBetween(int min, int max);
	
	// Buscar productos que no se hayan comprado y que contengan una cadena de caracteres
	List<Product> findByNameContainsIgnoreCaseAndOrderIsNull(String name);
	
	// Buscar productos por cadena de caracteres y propietario
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, UserHoop owner);

}
