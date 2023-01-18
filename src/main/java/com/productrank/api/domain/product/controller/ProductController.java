package com.productrank.api.domain.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ProductController {
    @GetMapping("/test")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
