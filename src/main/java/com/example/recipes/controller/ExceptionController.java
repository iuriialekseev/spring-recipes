package com.example.recipes.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController implements ErrorController {
    @GetMapping("/error")
    public String show(Model model, HttpServletRequest request) {
        Object code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(code.toString()));

        model.addAttribute("status", code);

        switch (status) {
            case UNAUTHORIZED -> {
                model.addAttribute("reason", "Insufficient rights");
                model.addAttribute("explanation", "You are not authorized to access this page.");
            }
            case NOT_FOUND -> {
                model.addAttribute("reason", "Page not found");
                model.addAttribute("explanation", "The page you are looking for does not exist.");
            }
            default -> {
                model.addAttribute("reason", "Internal server error");
                model.addAttribute("explanation", "An internal server error occurred.");
            }
        }

        return "error";
    }
}