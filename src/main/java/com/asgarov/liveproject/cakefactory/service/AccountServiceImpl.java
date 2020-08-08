package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Role;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(propagation = SUPPORTS)
    public boolean saveAccount(SignupDTO signupDTO) {
        return saveAccount(extractAccount(signupDTO));
    }

    @Override
    public boolean saveAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.addRole(Role.USER);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean updateAccount(Account account) {
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    private Account extractAccount(SignupDTO signupDTO) {
        return Account.builder().firstName(signupDTO.getFirstName())
                .lastName(signupDTO.getLastName())
                .email(signupDTO.getEmail())
                .password(signupDTO.getPassword())
                .build();
    }
}
