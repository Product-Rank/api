package com.productrank.api.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("");
    }
}
