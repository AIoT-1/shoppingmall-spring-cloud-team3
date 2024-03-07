package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TestEntityManager em;
    @Test
    @DisplayName("존재하는 로그인 아이디 테스트")
    void existsByLoginId() {
        em.persist(User.builder().loginId("test").build());
        boolean exists = userRepository.existsByLoginId("test");
        assertTrue(exists);
    }
    @Test
    @DisplayName("존재하지 않는 로그인 아이디 테스트")
    void notExistsByLoginId() {
        boolean exists = userRepository.existsByLoginId("test");
        assertFalse(exists);
    }

    @Test
    @DisplayName("로그인 아이디로 사용자 조회 테스트")
    void findByLoginId() {
        User user = User.builder().loginId("test").build();
        em.persist(user);
        User foundUser = userRepository.findByLoginId("test").get();
        assertEquals(user, foundUser);
    }
}