package com.example.recipes.repository;

import com.example.recipes.entity.Product;
import com.example.recipes.enums.ProductUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByNameAndUnit(String name, ProductUnit unit);
}
