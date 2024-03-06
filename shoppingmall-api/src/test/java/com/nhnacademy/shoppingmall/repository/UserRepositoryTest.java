package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager em;
    private Pageable pageable;
    @BeforeEach
    public void setUp() {
        pageable = PageRequest.of(0,9);
        Category persistedCategory = em.persist(Category.builder()
                .name("카테고리")
                .build());
        Product persistedProduct = em.persist(Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .description("description")
                .thumbnail("thumbnail")
                .build());
        ProductCategory persistedProductCategory = em.persist(ProductCategory.builder()
                .productId(persistedProduct.getId())
                .categoryId(persistedCategory.getId())
                .build());
    }

    @AfterEach
    public void tearDown() {
        em.clear();
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 X , 카테고리 X)")
    void findProductPage (){

        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, null, null);

        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getContent()).isNotNull();
        Assertions.assertThat(page.getContent()).hasSize(1);
        Assertions.assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");


    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 O , 카테고리 X)")
    void findProductPageWithKeyword (){

        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, "model", null);

        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getContent()).isNotNull();
        Assertions.assertThat(page.getContent()).hasSize(1);
        Assertions.assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어(없는 modelName) O , 카테고리 X)")
    void findProductPageWithKeywordFailed (){

        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, "pzazz", null);

        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getContent()).isNotNull();
        Assertions.assertThat(page.getContent()).isEmpty();
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 X , 카테고리 O)")
    void findProductPageWithCategory (){

        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, null, 1L);

        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getContent()).isNotNull();
        Assertions.assertThat(page.getContent()).hasSize(1);
        Assertions.assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 O , 카테고리 O)")
    void findProductPageWithKeywordAndCategory (){
        Category persistedCategory = em.persist(Category.builder()
                .name("카테고리")
                .build());
        Product persistedProduct = em.persist(Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .description("description")
                .thumbnail("thumbnail")
                .build());
        ProductCategory persistedProductCategory = em.persist(ProductCategory.builder()
                .productId(persistedProduct.getId())
                .categoryId(persistedCategory.getId())
                .build());
        Page<ProductDto.ProductSummaryResponse> page =  productRepository.findProductSummaryBySearchOption(pageable, "model", persistedCategory.getId());

        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(page.getContent()).isNotNull();
        Assertions.assertThat(page.getContent()).hasSize(1);
        Assertions.assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");

    }


}