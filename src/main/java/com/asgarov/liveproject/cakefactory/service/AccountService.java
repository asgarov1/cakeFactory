package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;

public interface AccountService {

    boolean saveAccount(SignupDTO signupDTO);
    boolean saveAccount(Account account);
    boolean updateAccount(Account account);
    Account findByEmail(String email);
}
