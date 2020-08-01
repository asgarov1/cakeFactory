package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class AddressServiceImplTest {

    @Autowired
    AddressRepository addressRepository;

    AddressService addressService;

    @BeforeEach
    void setUp() {
        addressService = new AddressServiceImpl(addressRepository);
    }

    @Test
    @DisplayName("saveAddress() should work")
    void saveAddress() {
        SignupDTO signupDTO = SignupDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@gmail.com")
                .password("123456")
                .repeatPassword("123456")
                .address("Main str 123")
                .country("Australia")
                .city("Melbourne")
                .zip("TSUPM8")
                .build();

        addressService.saveAddress(signupDTO);
        assertNotNull(addressService.findByEmail(signupDTO.getEmail()));
    }

    @Test
    @DisplayName("saveAccount() should work")
    void findByEmail() {
        Address address = new Address("john@mail.ru", "Main str 123", "Australia", "Melbourne", "TSUPM8");
        addressService.saveAddress(address);
        assertEquals(address, addressService.findByEmail(address.getEmail()));
    }
}