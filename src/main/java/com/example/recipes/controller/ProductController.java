package com.example.recipes.controller;

import com.example.recipes.entity.Product;
import com.example.recipes.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/index";
    }

    @GetMapping("/products/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping("/products")
    public String create(@Valid Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.findById(id));
        return "products/edit";
    }

    @PutMapping("/products/{id}")
    public String update(@Valid Product productParams, @PathVariable int id) {
        Product product = productService.findById(id);
        product.setName(productParams.getName());
        product.setUnit(productParams.getUnit());
        productService.save(product);
        return "redirect:/products";
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
