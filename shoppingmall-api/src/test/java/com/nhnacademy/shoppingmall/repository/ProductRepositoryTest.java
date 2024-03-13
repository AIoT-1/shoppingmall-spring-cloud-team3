package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.*;

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

    }

    @AfterEach
    public void tearDown() {
        em.clear();
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 X , 카테고리 X)")
    void findProductPage (){
        Product persistedProduct = em.persist(createProduct());
        Category persistedCategory = em.persist(createCategory());
        em.persist(new ProductCategory(persistedProduct, persistedCategory));
        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, null, null);

        assertThat(page).isNotNull();
        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");


    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 O , 카테고리 X)")
    void findProductPageWithKeyword (){
        Product persistedProduct = em.persist(createProduct());
        Category persistedCategory = em.persist(createCategory());
       em.persist(new ProductCategory(persistedProduct, persistedCategory));
        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, "model", null);

        assertThat(page).isNotNull();
        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어(없는 modelName) O , 카테고리 X)")
    void findProductPageWithKeywordFailed (){
        Product persistedProduct = em.persist(createProduct());
        Category persistedCategory = em.persist(createCategory());
        em.persist(new ProductCategory(persistedProduct, persistedCategory));
        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, "pzazz", null);

        assertThat(page).isNotNull();
        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent()).isEmpty();
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 X , 카테고리 O)")
    void findProductPageWithCategory (){
        Product persistedProduct = em.persist(createProduct());
        Category persistedCategory = em.persist(createCategory());
        em.persist(new ProductCategory(persistedProduct, persistedCategory));
        Page<ProductDto.ProductSummaryResponse> page = productRepository.findProductSummaryBySearchOption(pageable, null, persistedCategory.getId());

        assertThat(page).isNotNull();
        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");
    }

    @Test
    @DisplayName("상품 목록 페이지 조회 테스트(검색어 O , 카테고리 O)")
    void findProductPageWithKeywordAndCategory (){
        Product persistedProduct = em.persist(createProduct());
        Category persistedCategory = em.persist(createCategory());
        em.persist(new ProductCategory(persistedProduct, persistedCategory));
        Page<ProductDto.ProductSummaryResponse> page =  productRepository.findProductSummaryBySearchOption(pageable, "model", persistedCategory.getId());

        assertThat(page).isNotNull();
        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getModelNumber()).isEqualTo("modelNumber");

    }
    private Category createCategory() {
        return Category.builder()
                .name("test")
                .build();
    }
    private Product createProduct() {
        return Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .description("description")
                .thumbnail("thumbnail")
                .build();
    }




}