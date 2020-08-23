package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.event.RegistrationEvent;
import com.asgarov.liveproject.cakefactory.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Value("${signup.success}")
    private String SIGNUP_SUCCESS_MESSAGE;

    @Value("${user.exists}")
    private String USER_EXISTS_MESSAGE;

    private final AccountService accountService;

    private final ApplicationEventPublisher eventPublisher;

    public SignupController(AccountService accountService, ApplicationEventPublisher eventPublisher) {
        this.accountService = accountService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public String goToSignupPage() {
        return "signup";
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String signup(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String addressLine,
                         @RequestParam String city,
                         @RequestParam String country,
                         @RequestParam String zip) {
        try {
            accountService.findByEmail(email);
            return USER_EXISTS_MESSAGE;
        } catch (EntityNotFoundException e) {
            eventPublisher.publishEvent(new RegistrationEvent(SignupDTO.builder().firstName(firstName)
                    .lastName(lastName).email(email).password(password).address(addressLine).city(city)
                    .country(country).zip(zip).build()));
            return SIGNUP_SUCCESS_MESSAGE;
        }
    }
}
