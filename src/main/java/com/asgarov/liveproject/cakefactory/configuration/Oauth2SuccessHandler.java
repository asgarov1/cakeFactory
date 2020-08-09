package com.asgarov.liveproject.cakefactory.configuration;

import com.asgarov.liveproject.cakefactory.domain.Account;
import com.asgarov.liveproject.cakefactory.domain.Address;
import com.asgarov.liveproject.cakefactory.domain.Role;
import com.asgarov.liveproject.cakefactory.domain.User;
import com.asgarov.liveproject.cakefactory.repository.AccountRepository;
import com.asgarov.liveproject.cakefactory.repository.AddressRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public Oauth2SuccessHandler(AccountRepository accountRepository, AddressRepository addressRepository) {
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oAuth2User = oToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        if (accountRepository.findByEmail(email).isEmpty()) {
            String name = oAuth2User.getAttribute("name");
            String firstName = name;
            String lastName = "";
            if(name != null && name.contains(" ")){
                firstName = name.split(" ")[0];
                lastName = name.split(" ")[1];
            }

            Account account = Account.builder().email(email).firstName(firstName).lastName(lastName).roles(Set.of(Role.USER)).build();
            accountRepository.save(account);
        }
        User user = User.builder()
                .account(accountRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new))
                .address(addressRepository.findByEmail(email).orElseGet(Address::new))
                .build();

        Authentication auth =
                new OAuth2AuthenticationToken(oToken.getPrincipal(), user.getAuthorities(), oToken.getAuthorizedClientRegistrationId());
        SecurityContextHolder.getContext().setAuthentication(auth);

        this.redirectStrategy.sendRedirect(request, response, "/account");
    }
}
