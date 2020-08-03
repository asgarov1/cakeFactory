package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(propagation = SUPPORTS)
    public boolean saveAddress(SignupDTO signupDTO) {
        addressRepository.save(extractAddress(signupDTO));
        return true;
    }

    @Override
    public boolean saveAddress(Address address) {
        addressRepository.save(address);
        return true;
    }

    @Override
    public Optional<Address> findByEmail(String email) {
        return addressRepository.findByEmail(email);
    }

    private Address extractAddress(SignupDTO signupDTO) {
        return Address.builder().addressLine(signupDTO.getAddress())
                .city(signupDTO.getCity())
                .country(signupDTO.getCountry())
                .zip(signupDTO.getZip())
                .email(signupDTO.getEmail())
                .build();
    }
}
