package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SignupServiceImpl implements SignupService {

    private final AccountService accountService;
    private final AddressService addressService;

    public SignupServiceImpl(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @Transactional
    public boolean signupUser(SignupDTO signupDTO) {
        return accountService.saveAccount(signupDTO) &&
                addressService.saveAddress(signupDTO);
    }

}
