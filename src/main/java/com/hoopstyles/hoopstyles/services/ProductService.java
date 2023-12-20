package com.hoopstyles.hoopstyles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repositorio;
	
	public Product insert(Product p) {
		return repositorio.save(p);
	}
	
	public void delete(Product p) {
		repositorio.delete(p);
	}
	
	public void delete(long id) {
		repositorio.deleteById(id);
	}
	
	public Product edit(Product p) {
		return repositorio.save(p);
	}
	
	public Product findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

    public long countProducts() {
        return repositorio.count();
    }

    public void save(Product product) {
        repositorio.save(product);
    }

    public List<Product> findByPriceBetween(int min, int max) {
        return repositorio.findByPriceBetween(min, max);
    }

    public List<Product> findByCategory(Category categories) {
        return repositorio.findByCategoriesIn(List.of(categories));
    }

    public List<Product> findByCategory(List<Category> categories) {
        return repositorio.findByCategoriesIn(categories);
    }

    public List<Product> findByPriceBetweenAndCategory(int min, int max, List<Category> categories) {
        return repositorio.findByPriceBetweenAndCategoriesIn(min, max, categories);
    }
	
	public List<Product> all() {
		return repositorio.findAll();
	}
	
	public List<Product> searchMyProducts(String query, UserHoop u) {
		return repositorio.findByNameContainsIgnoreCaseAndOwner(query,u);
	}
	
	public List<Product> variousById(List<Long> ids) {
		return repositorio.findAllById(ids);
	}

    public List<Product> search(String query) {
        return repositorio.findByNameContainsIgnoreCase(query);
    }

    public List<Product> search(String query, List<Category> categories) {
        return repositorio.findByNameContainsIgnoreCaseAndCategoriesIn(query, categories);
    }
}
