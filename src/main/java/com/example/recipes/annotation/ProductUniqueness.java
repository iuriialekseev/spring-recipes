package com.example.recipes.annotation;

import com.example.recipes.validation.ProductUniquenessValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductUniquenessValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductUniqueness {
    String message() default "Product already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}