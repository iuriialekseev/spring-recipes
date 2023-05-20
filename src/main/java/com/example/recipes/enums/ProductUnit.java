package com.example.recipes.enums;

public enum ProductUnit {
    GRAM("Gram"),
    MILLILITER("Milliliter"),
    ITEM("Item");

    private String displayName;

    private ProductUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
