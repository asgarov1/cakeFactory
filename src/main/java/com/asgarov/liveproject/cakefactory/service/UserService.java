package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.domain.dto.UpdateDTO;

public interface UserService {
    public User findUser(String email);
    public boolean updateUser(String email, UpdateDTO updateDTO);
}
