package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository userRepository) {
        this.accountRepository = userRepository;
    }

    @Override
    @Transactional(propagation = SUPPORTS)
    public boolean saveAccount(SignupDTO signupDTO) {
        accountRepository.save(extractAccount(signupDTO));
        return true;
    }

    @Override
    public boolean saveAccount(Account account) {
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    private Account extractAccount(SignupDTO signupDTO) {
        return Account.builder().firstName(signupDTO.getFirstName())
                .lastName(signupDTO.getLastName())
                .email(signupDTO.getEmail())
                .password(signupDTO.getPassword())
                .build();
    }
}
