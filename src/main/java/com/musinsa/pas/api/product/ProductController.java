package com.musinsa.pas.api.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> productSearch() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
