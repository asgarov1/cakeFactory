package com.asgarov.liveproject.cakefactory.controllers;

import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String viewBasket(Model model){
        model.addAttribute("basketEntries", basketService.getBasketItems());
        model.addAttribute("total", String.format("%.2f", basketService.getTotal()));
        model.addAttribute("numberOfItemsInBasket", basketService.countItems());
        model.addAttribute("orderDTO", new OrderDTO());
        return "basket";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String itemCode) {
        basketService.removeItemFromBasket(catalogService.findById(itemCode));
        return "redirect:/basket";
    }
}
