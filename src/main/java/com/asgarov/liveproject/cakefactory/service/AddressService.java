package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;

import java.util.Optional;

public interface AddressService {

    boolean saveAddress(SignupDTO signupDTO);
    boolean saveAddress(Address address);
    Optional<Address> findByEmail(String email);
}
