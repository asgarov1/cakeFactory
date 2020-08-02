package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.repository.AccountRepository;
import com.asgarov.liveproject.cakefactory.repository.AddressRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    public CustomUserDetailsService(AccountRepository accountRepository, AddressRepository addressRepository) {
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByEmail(username);
        if(optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + "not found");
        }

        List<Address> addresses = addressRepository.findByEmail(username);

        return new User(optionalAccount.get(), addresses);
    }
}
