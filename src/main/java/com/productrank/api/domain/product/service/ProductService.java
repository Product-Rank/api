package com.productrank.api.domain.product.service;

import com.productrank.api.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public boolean test() {
        return false;
    }
}
