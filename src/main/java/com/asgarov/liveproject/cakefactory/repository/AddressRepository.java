package com.asgarov.liveproject.cakefactory.repository;

import com.asgarov.liveproject.cakefactory.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
    Address findByEmail(String email);
}
