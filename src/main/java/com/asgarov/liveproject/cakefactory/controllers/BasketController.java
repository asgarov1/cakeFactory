package com.asgarov.liveproject.cakefactory.controllers;

import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    private final CatalogService catalogService;

    public BasketController(BasketService basketService, CatalogService catalogService) {
        this.basketService = basketService;
        this.catalogService = catalogService;
    }

    @PostMapping
    public String addItem(@RequestParam String itemCode){
        basketService.addItemToBasket(catalogService.findById(itemCode));
        return "redirect:/";
    }
}
