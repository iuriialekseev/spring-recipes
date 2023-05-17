package com.example.recipes.controller;

import com.example.recipes.entity.Product;
import com.example.recipes.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/index";
    }

    @GetMapping("/new")
    public String newAction(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping
    public String create(@Valid Product params, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", params);
            return "products/new";
        }
        productService.save(params);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @Valid Product params, BindingResult result, Model model) {
        Product product = productService.findById(id);
        if (result.hasErrors()) {
            model.addAttribute("product", params);
            return "products/edit";
        }
        params.setId(product.getId());
        productService.save(params);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
