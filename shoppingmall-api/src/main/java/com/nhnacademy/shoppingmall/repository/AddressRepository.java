package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
