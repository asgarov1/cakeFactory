package com.asgarov.liveproject.cakefactory.controllers;

import com.asgarov.liveproject.cakefactory.entity.Product;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final CatalogService catalogService;

    public HomeController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping(value = {"/"})
    public String home() {
        return "index";
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return catalogService.findAll();
    }
}
