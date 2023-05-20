package com.example.recipes.validation;

import com.example.recipes.annotation.ProductUniqueness;
import com.example.recipes.entity.Product;
import com.example.recipes.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductUniquenessValidator implements ConstraintValidator<ProductUniqueness, Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if (product == null || productRepository == null) return true;
        return productRepository.findByNameAndUnit(product.getName(), product.getUnit()) == null;
    }
}
