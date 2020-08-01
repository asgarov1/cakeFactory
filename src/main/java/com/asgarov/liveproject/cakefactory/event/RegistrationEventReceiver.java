package com.asgarov.liveproject.cakefactory.event;

import com.asgarov.liveproject.cakefactory.service.SignupService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventReceiver {
    private final SignupService signupService;

    public RegistrationEventReceiver(SignupService signupService) {
        this.signupService = signupService;
    }

    @EventListener
    public void onRegistration(RegistrationEvent registrationEvent) {
        signupService.signupUser(registrationEvent.getSignupDTO());
    }
}
