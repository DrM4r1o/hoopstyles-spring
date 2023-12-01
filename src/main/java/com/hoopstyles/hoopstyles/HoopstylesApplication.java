package com.hoopstyles.hoopstyles;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.repository.CategoryRepository;
import com.hoopstyles.hoopstyles.repository.ProductRepository;
import com.hoopstyles.hoopstyles.services.CategoryService;
import com.hoopstyles.hoopstyles.services.ProductService;

@SpringBootApplication
public class HoopstylesApplication {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(HoopstylesApplication.class, args);
    }

    @Bean
    public CommandLineRunner categories() {
        if(categoryService.countCategories() > 0) {
            return null;
        }
        return (args) -> {
            categoryService.save(new Category("Trousers", "Trousers category"));
            categoryService.save(new Category("Tees", "Tees category"));
            categoryService.save(new Category("Hoodies", "Hoodies category"));
            categoryService.save(new Category("Shoes", "Shoes category"));
            categoryService.save(new Category("Accessories", "Accessories category"));
        };
    }

    @Bean
    public CommandLineRunner products() {
        Category trousers = categoryService.findByName("Trousers");
        Category tees = categoryService.findByName("Tees");
        Category hoodies = categoryService.findByName("Hoodies");
        Category shoes = categoryService.findByName("Shoes");
        Category accessories = categoryService.findByName("Accessories");

        if (productService.countProducts() > 0 || Arrays.asList(trousers, tees, hoodies, shoes, accessories).contains(null)) {
            return null;
        }
        
        return (args) -> {
            productService.save(new Product("Trousers LA", 45.2f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple", List.of(trousers)));
            productService.save(new Product("Melo 03 Toxic", 135.0f, "https://24segons.es/178237-large_default/puma-mb03-toxic-zapatillas.jpg", "Descroption exapmple", List.of(shoes)));
            productService.save(new Product("Air Jordan 1 MID", 150.0f, "https://24segons.es/180137-large_default/item-FB9911001.jpg", "Descroption exapmple", List.of(shoes)));
            productService.save(new Product("Trousers 4", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple", List.of(trousers)));
            productService.save(new Product("Trousers 5", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple", List.of(trousers)));
        };
    }

}

