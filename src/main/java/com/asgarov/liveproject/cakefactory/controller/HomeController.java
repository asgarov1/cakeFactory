package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.OrderItem;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final BasketService basketService;
    private final CatalogService catalogService;

    public HomeController(BasketService basketService, CatalogService catalogService) {
        this.basketService = basketService;
        this.catalogService = catalogService;
    }

    @GetMapping(value = {"/", "index"})
    public String home(Model model) {
        if (!basketService.getBasketItems().isEmpty()) {
            model.addAttribute("showBasket", true);
        }
        return "index";
    }

    @ModelAttribute("orderItems")
    public List<OrderItem> products() {
        return catalogService.findAll();
    }

    @ModelAttribute("numberOfItemsInBasket")
    public Integer numberOfItemsInBasket() {
        return basketService.countItems();
    }

}
