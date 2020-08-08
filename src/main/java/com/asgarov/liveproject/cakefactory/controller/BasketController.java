package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.OrderDTO;
import com.asgarov.liveproject.cakefactory.service.BasketService;
import com.asgarov.liveproject.cakefactory.service.CatalogService;
import com.asgarov.liveproject.cakefactory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    private final CatalogService catalogService;
    private final UserService userService;

    public BasketController(BasketService basketService, CatalogService catalogService, UserService userService) {
        this.basketService = basketService;
        this.catalogService = catalogService;
        this.userService = userService;
    }

    @PostMapping
    public String addItem(@RequestParam String itemCode) {
        basketService.addItemToBasket(catalogService.findById(itemCode));
        return "redirect:/";
    }

    @GetMapping
    public String viewBasket(Model model) {
        model.addAttribute("basketEntries", basketService.getBasketItems());
        model.addAttribute("total", String.format("%.2f", basketService.getTotal()));
        model.addAttribute("numberOfItemsInBasket", basketService.countItems());
        model.addAttribute("orderDTO", new OrderDTO());
        model.addAttribute("user", userService.findUser(Objects.requireNonNull(model.getAttribute("email")).toString()));
        return "basket";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String itemCode) {
        basketService.removeItemFromBasket(catalogService.findById(itemCode));
        return "redirect:/basket";
    }
}
