package com.asgarov.liveproject.cakefactory.service.user;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;

public interface SignupService {
    boolean signupUser(SignupDTO signupDTO);
}