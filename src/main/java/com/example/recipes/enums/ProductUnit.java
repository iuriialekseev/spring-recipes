package com.example.recipes.enums;

public enum ProductUnit {
    GRAM("Gram"),
    KILOGRAM("Kilogram"),
    MILLILITER("Milliliter"),
    LITER("Liter"),
    ITEM("Item");

    private String displayName;

    private ProductUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
