package com.hoopstyles.hoopstyles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.model.Product;
import com.hoopstyles.hoopstyles.repository.CategoryRepository;
import com.hoopstyles.hoopstyles.repository.ProductRepository;

@SpringBootApplication
public class HoopstylesApplication {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(HoopstylesApplication.class, args);
    }

    @Bean
    public CommandLineRunner categories() {
        if(categoryRepository.count() > 0) {
            return null;
        }
        return (args) -> {
            categoryRepository.save(new Category("Trousers", "Trousers category"));
            categoryRepository.save(new Category("Tees", "Tees category"));
            categoryRepository.save(new Category("Hoodies", "Hoodies category"));
            categoryRepository.save(new Category("Shoes", "Shoes category"));
            categoryRepository.save(new Category("Accessories", "Accessories category"));
        };
    }

    @Bean
    public CommandLineRunner products() {
        if(productRepository.count() > 0) {
            return null;
        }
        return (args) -> {
            productRepository.save(new Product("Trousers 1", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple"));
            productRepository.save(new Product("Trousers 2", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple"));
            productRepository.save(new Product("Trousers 3", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple"));
            productRepository.save(new Product("Trousers 4", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple"));
            productRepository.save(new Product("Trousers 5", 20.0f, "https://hoopstyles.com/cdn/shop/files/8ace79a7c36290cee2a1858b95400e96.jpg?v=1697242974&width=823", "Descroption exapmple"));
        };
    }

}

