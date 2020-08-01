package com.asgarov.liveproject.cakefactory.event;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationEvent {
    private final SignupDTO signupDTO;
}
