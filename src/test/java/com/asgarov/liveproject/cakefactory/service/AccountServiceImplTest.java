package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class AccountServiceImplTest {

    @Autowired
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    @DisplayName("saveAccount() should work")
    void saveAccount() {
        SignupDTO signupDTO = SignupDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@gmail.com")
                .password("123456")
                .repeatPassword("123456")
                .build();

        accountService.saveAccount(signupDTO);
        assertNotNull(accountService.findByEmail(signupDTO.getEmail()));
    }

    @Test
    @DisplayName("saveAccount() should work")
    void findByEmail() {
        Account account = new Account("john@mail.ru", "pass", "John", "Smith");
        accountService.saveAccount(account);
        assertEquals(account, accountService.findByEmail(account.getEmail()));
    }
}