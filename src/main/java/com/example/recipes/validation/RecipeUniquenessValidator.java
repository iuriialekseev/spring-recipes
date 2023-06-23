package com.example.recipes.validation;

import com.example.recipes.annotation.RecipeUniqueness;
import com.example.recipes.entity.Recipe;
import com.example.recipes.repository.RecipeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeUniquenessValidator implements ConstraintValidator<RecipeUniqueness, Recipe> {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public boolean isValid(Recipe recipe, ConstraintValidatorContext constraintValidatorContext) {
        if (recipe == null || recipeRepository == null) return true;
        Recipe existingProduct = recipeRepository.findByName(recipe.getName());
        return existingProduct == null || existingProduct.getId() == recipe.getId();
    }
}
