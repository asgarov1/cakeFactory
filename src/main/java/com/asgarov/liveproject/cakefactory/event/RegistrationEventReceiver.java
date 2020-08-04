package com.asgarov.liveproject.cakefactory.event;

import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.service.CustomUserDetailsService;
import com.asgarov.liveproject.cakefactory.service.SignupService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventReceiver {
    private final SignupService signupService;
    private final CustomUserDetailsService userDetailsService;

    public RegistrationEventReceiver(SignupService signupService, CustomUserDetailsService userDetailsService) {
        this.signupService = signupService;
        this.userDetailsService = userDetailsService;
    }

    @EventListener
    public void onRegistration(RegistrationEvent registrationEvent) {
        SignupDTO signupDTO = registrationEvent.getSignupDTO();
        signupService.signupUser(signupDTO);
        User user = (User) userDetailsService.loadUserByUsername(signupDTO.getEmail());
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
