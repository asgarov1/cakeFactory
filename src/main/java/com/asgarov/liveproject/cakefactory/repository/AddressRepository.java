package com.asgarov.liveproject.cakefactory.repository;

import com.asgarov.liveproject.cakefactory.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByEmail(String email);
}
