package com.hoopstyles.hoopstyles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hoopstyles.hoopstyles.model.Category;
import com.hoopstyles.hoopstyles.repository.CategoryRepository;

@SpringBootApplication
public class HoopstylesApplication {

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(HoopstylesApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
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
}

