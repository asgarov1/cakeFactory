package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.event.RegistrationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Value("${signup.success}")
    private String SIGNUP_SUCCESS_MESSAGE;

    private final ApplicationEventPublisher eventPublisher;

    public SignupController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public String goToSignupPage() {
        return "signup";
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String signup(@RequestBody SignupDTO signupDTO) {
        eventPublisher.publishEvent(new RegistrationEvent(signupDTO));
        return SIGNUP_SUCCESS_MESSAGE;
    }
}
