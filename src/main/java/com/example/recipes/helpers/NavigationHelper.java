package com.example.recipes.helpers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("nav")
public class NavigationHelper {
    @Autowired
    private HttpServletRequest request;

    public String navClass(String path) {
        return request.getRequestURI().startsWith(path) ? "active" : "";
    }
}
