package com.example.recipes.pojo;

import com.example.recipes.enums.FlashType;

public class Flash {
    private FlashType type;
    private String message;

    public Flash(FlashType type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type.toString().toLowerCase();
    }
}