package com.example.recipes.controller;

import com.example.recipes.entity.Product;
import com.example.recipes.enums.FlashType;
import com.example.recipes.pojo.Flash;
import com.example.recipes.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@Valid Product params, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("product", params);
            return "products/new";
        }
        productService.save(params);
        Flash flash = new Flash(FlashType.SUCCESS, "Created product");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @Valid Product params, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (result.hasErrors()) {
            model.addAttribute("product", params);
            return "products/edit";
        }
        params.setId(product.getId());
        productService.save(params);
        Flash flash = new Flash(FlashType.SUCCESS, "Updated product");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        productService.deleteById(id);
        Flash flash = new Flash(FlashType.SUCCESS, "Deleted product");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/products";
    }
}