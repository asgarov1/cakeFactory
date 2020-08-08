package com.asgarov.liveproject.cakefactory.service;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.domain.dto.SignupDTO;
import com.asgarov.liveproject.cakefactory.domain.dto.UpdateDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements SignupService, UserService {

    private final AccountService accountService;
    private final AddressService addressService;

    public UserServiceImpl(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @Override
    @Transactional
    public boolean signupUser(SignupDTO signupDTO) {
        return accountService.saveAccount(signupDTO) &&
                addressService.saveAddress(signupDTO);
    }

    @Override
    public User findUser(String email) {
        Account account = accountService.findByEmail(email);
        Address address = addressService.findByEmail(email).orElseGet(Address::new);
        return new User(account, address);
    }

    @Override
    public boolean updateUser(String email, UpdateDTO updateDTO) {
        Account account = accountService.findByEmail(email);
        account.setFirstName(updateDTO.getFirstName());
        account.setLastName(updateDTO.getLastName());
        accountService.updateAccount(account);

        Optional<Address> addressOptional = addressService.findByEmail(email);

        Address address = addressOptional.orElseGet(() -> Address.builder().email(email).build());
        address.setAddressLine(updateDTO.getAddress());
        address.setCountry(updateDTO.getCountry());
        address.setCity(updateDTO.getCity());
        address.setZip(updateDTO.getZip());
        addressService.saveAddress(address);
        return true;
    }
}
