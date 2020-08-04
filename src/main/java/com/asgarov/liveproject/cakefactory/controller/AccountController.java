package com.asgarov.liveproject.cakefactory.controller;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.domain.dto.UpdateDTO;
import com.asgarov.liveproject.cakefactory.service.AccountService;
import com.asgarov.liveproject.cakefactory.service.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private AddressService addressService;

    public AccountController(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @Value("${update.success}")
    private String UPDATE_SUCCESS_MESSAGE;

    @GetMapping
    public String account(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateAccount(@RequestBody UpdateDTO updateDTO, @AuthenticationPrincipal User user){
        Account account = user.getAccount();
        account.setFirstName(updateDTO.getFirstName());
        account.setLastName(updateDTO.getLastName());
        accountService.saveAccount(account);

        Address address = user.getAddress();
        address.setAddressLine(updateDTO.getAddress());
        address.setCountry(updateDTO.getCountry());
        address.setCity(updateDTO.getCity());
        address.setZip(updateDTO.getZip());
        addressService.saveAddress(address);

        return UPDATE_SUCCESS_MESSAGE;
    }
}
