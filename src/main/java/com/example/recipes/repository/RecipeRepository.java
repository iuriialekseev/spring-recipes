package com.example.recipes.repository;

import com.example.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByName(String name);
}
