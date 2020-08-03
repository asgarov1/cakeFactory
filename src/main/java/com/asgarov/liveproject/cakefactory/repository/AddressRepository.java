package com.asgarov.liveproject.cakefactory.repository;

import com.asgarov.liveproject.cakefactory.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByEmail(String email);
}
