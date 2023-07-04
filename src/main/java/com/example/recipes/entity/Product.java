package com.example.recipes.entity;

import com.example.recipes.annotation.ProductUniqueness;
import com.example.recipes.enums.ProductUnit;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@ProductUniqueness
public class Product extends BaseEntity {
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<RecipeProduct> recipeProducts = new ArrayList<>();

    @NotBlank
    @Size(max = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductUnit unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                ", createdDate=" + getCreatedDate() +
                ", modifiedDate=" + getModifiedDate() +
                '}';
    }
}
