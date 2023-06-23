package com.example.recipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "recipe_products")
public class RecipeProduct extends BaseEntity {
    private Long recipeId;
    private Long productId;
    private BigDecimal quantity;
}
