package com.asgarov.liveproject.cakefactory.controller.advice;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AuthenticationAdvice {

    @ModelAttribute
    public void loggedIn(Model model, Authentication authentication) {
        if (authentication != null) {
            String email = ((OAuth2User) authentication.getPrincipal()).getAttribute("email");
            model.addAttribute("email", email);
        }
    }
}
