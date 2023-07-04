package com.example.recipes.entity;

import com.example.recipes.annotation.RecipeUniqueness;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@RecipeUniqueness
public class Recipe extends BaseEntity {
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<RecipeProduct> recipeProducts = new ArrayList<>();

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecipeProduct> getRecipeProducts() {
        return recipeProducts;
    }

    public void setRecipeProducts(List<RecipeProduct> recipeProducts) {
        this.recipeProducts = recipeProducts;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                '}';
    }
}
