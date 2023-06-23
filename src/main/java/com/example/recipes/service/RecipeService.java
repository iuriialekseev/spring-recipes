package com.example.recipes.service;

import com.example.recipes.entity.Recipe;
import com.example.recipes.exception.NotFoundException;
import com.example.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(int id) {
        return recipeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteById(int id) {
        recipeRepository.deleteById(id);
    }
}
