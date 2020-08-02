package com.asgarov.liveproject.cakefactory.repository;

import com.asgarov.liveproject.cakefactory.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByEmail(String email);
}
