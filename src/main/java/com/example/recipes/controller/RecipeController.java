package com.example.recipes.controller;

import com.example.recipes.entity.Recipe;
import com.example.recipes.enums.FlashType;
import com.example.recipes.pojo.Flash;
import com.example.recipes.service.ProductService;
import com.example.recipes.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final ProductService productService;

    @Autowired
    public RecipeController(RecipeService recipeService, ProductService productService) {
        this.recipeService = recipeService;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes/index";
    }

    @GetMapping("/new")
    public String newAction(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("products", productService.findAll());
        return "recipes/new";
    }

    @PostMapping
    public String create(@Valid Recipe recipeParams,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        recipeParams.getRecipeProducts().removeIf(rp -> rp.getProduct() == null || rp.getQuantity() == null);
        if (result.hasErrors()) {
            model.addAttribute("recipe", recipeParams);
            model.addAttribute("products", productService.findAll());
            System.out.println(result.getAllErrors());
            return "recipes/new";
        }
        recipeParams.getRecipeProducts().forEach(recipeProduct -> {
            recipeProduct.setRecipe(recipeParams);
        });
        recipeService.save(recipeParams);
        Flash flash = new Flash(FlashType.SUCCESS, "Created recipe");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/recipes";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("products", productService.findAll());
        return "recipes/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id,
                         @Valid Recipe recipeParams,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        Recipe editedRecipe = recipeService.findById(id);
        recipeParams.getRecipeProducts().removeIf(rp -> rp.getProduct() == null || rp.getQuantity() == null);
        if (result.hasErrors()) {
            model.addAttribute("recipe", recipeParams);
            model.addAttribute("products", productService.findAll());
            return "recipes/edit";
        }
        recipeParams.setId(editedRecipe.getId());
        recipeParams.getRecipeProducts().forEach(recipeProduct -> {
            recipeProduct.setRecipe(recipeParams);
        });
        recipeService.save(recipeParams);
        Flash flash = new Flash(FlashType.SUCCESS, "Updated recipe");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/recipes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        recipeService.deleteById(id);
        Flash flash = new Flash(FlashType.SUCCESS, "Deleted recipe");
        redirectAttributes.addFlashAttribute("flash", flash);
        return "redirect:/recipes";
    }
}