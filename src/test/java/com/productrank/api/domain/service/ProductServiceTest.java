package com.productrank.api.domain.service;

import com.productrank.api.config.security.custom.SecurityUser;
import com.productrank.api.domain.dto.ProductDto;
import com.productrank.api.domain.entity.Company;
import com.productrank.api.domain.entity.Product;
import com.productrank.api.domain.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
//@ActiveProfiles("private")
@DataJpaTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TestEntityManager entityManager;
    @Test
//    @WithMockUser
    void createProductTest(){
//        ProductService productService = new ProductService(productRepository);
//        ProductDto product = ProductDto.builder()
//                .build();
//        Company company = Company.builder()
//                .companyName("TEST")
//                .productList(new ArrayList<>())
//                .build();
//        Company companyEntity = entityManager.persist(company);
//
//        ProductDto productDto = productService.makeProduct(product, companyEntity);
//        assertThat(productDto).isNull();
//        assertThat(productDto.productName()).isEqualTo(product.getProductName());
//        assertThat(productDto.productDescription()).isEqualTo(product.getProductDescription());
//        assertThat(productDto.company()).isEqualTo(product.getCompany());
    }

    @Test
    void getProductListTest(){}

    @Test
    void searchProductListTest(){

    }

    @Test
    void getMyProductListTest(){
    }

    @Test
    void getProductDetailsTest(){

    }

    @Test
    void modifyProductDetailsTest(){

    }

    @Test
    void deleteProductTest(){}

}