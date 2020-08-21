package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.domain.dto.UpdateDTO;
import com.asgarov.liveproject.cakefactory.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Value("${update.success}")
    private String UPDATE_SUCCESS_MESSAGE;

    @Value("${update.failed}")
    private String UPDATE_FAILED_MESSAGE;

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String account(Authentication authentication, @AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            String email = ((OAuth2User) authentication.getPrincipal()).getAttribute("email");
            user = userService.findUser(email);
        }
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateAccount(@RequestBody UpdateDTO updateDTO, Model model) {
        //email is grabbed from authentication context so that loggedIn user could only update his own account
        String email = Objects.requireNonNull(model.getAttribute("email")).toString();

        if(userService.updateUser(email, updateDTO)) {
            return UPDATE_SUCCESS_MESSAGE;
        } else {
            return UPDATE_FAILED_MESSAGE;
        }
    }
}
