package com.asgarov.liveproject.cakefactory.bootstrap;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.Role;
import com.asgarov.liveproject.cakefactory.service.AccountService;
import com.asgarov.liveproject.cakefactory.service.AddressService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap {

    private AccountService accountService;
    private AddressService addressService;

    public Bootstrap(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @PostConstruct
    public void bootstrapDatabase() {
        String email = "admin@admin.com";

        try {
            accountService.findByEmail(email);
        } catch (EntityNotFoundException e) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            accountService.saveAccount(new Account(email, "pass", "Admin", "Admin", roles));
            addressService.saveAddress(Address.builder()
                    .email(email)
                    .addressLine("Main str. 123")
                    .country("USA")
                    .city("Boston")
                    .zip("BOS123").build());
        }
    }
}
