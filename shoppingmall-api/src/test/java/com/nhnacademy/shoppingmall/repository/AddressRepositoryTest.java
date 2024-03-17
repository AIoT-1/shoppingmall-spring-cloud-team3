package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("유저 아이디로 주소 목록 조회")
    void findAllByUserId() {
        User user = User.builder().build();
        testEntityManager.persist(user);

        Address address1 = Address.builder().user(user).address("address").addressDetail("detail").type("house").zipCode("12345").build();
        Address address2 = Address.builder().user(user).address("address").addressDetail("detail").type("house").zipCode("12345").build();
        testEntityManager.persist(address1);
        testEntityManager.persist(address2);

        assertThat(addressRepository.findAllByUser_Id(user.getId())).hasSize(2);
    }


    @Test
    @DisplayName("존재 하지 않는 유저 아이디로 주소 목록 조회")
    void findAllByNonExistingUserId() {
        assertThat(addressRepository.findAllByUser_Id(999L)).isEmpty();
    }



}