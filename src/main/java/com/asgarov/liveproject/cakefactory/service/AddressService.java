package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;

public interface AddressService {

    boolean saveAddress(SignupDTO signupDTO);
    boolean saveAddress(Address address);
    Address findByEmail(String email);
}
