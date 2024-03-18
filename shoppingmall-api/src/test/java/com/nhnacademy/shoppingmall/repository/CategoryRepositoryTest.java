package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("카테고리 이름 중복 확인(존재하는 경우)")
    void existsByName() {
        testEntityManager.persist(Category.builder().name("test").build());
        Assertions.assertThat(categoryRepository.existsByName("test")).isTrue();
    }
    @Test
    @DisplayName("카테고리 이름 중복 확인(존재하지 않는 경우)")
    void existsByName2() {
        testEntityManager.persist(Category.builder().name("test").build());
        Assertions.assertThat(categoryRepository.existsByName("test2")).isFalse();
    }

    @Test
    @DisplayName("카테고리 리스트 조회")
    void findAllBy() {
        testEntityManager.persist(Category.builder().name("test").build());
        testEntityManager.persist(Category.builder().name("test2").build());
        Assertions.assertThat(categoryRepository.findAll()).hasSize(2);
    }
}