package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;

import java.util.List;

public interface AddressService {

    boolean saveAddress(SignupDTO signupDTO);
    boolean saveAddress(Address address);
    List<Address> findByEmail(String email);
}
