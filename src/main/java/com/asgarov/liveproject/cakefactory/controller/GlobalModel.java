package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModel {

    @ModelAttribute
    public void loggedIn(Model model, @AuthenticationPrincipal User user){
        if(user != null) {
            model.addAttribute("email", user.getUsername());
        }
    }
}
