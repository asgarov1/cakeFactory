package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Address address = Address.builder().email("john@mail.ru").addressLine("Main str 123").country("Australia").city("Melbourne").zip("TSUPM8").build();
        addressService.saveAddress(address);

        Optional<Address> addressOptional = addressService.findByEmail(address.getEmail());
        assertTrue(addressOptional.isPresent());
        assertEquals(address, addressOptional.get());
    }
}