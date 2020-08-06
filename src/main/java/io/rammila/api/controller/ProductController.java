package io.rammila.api.controller;

import io.rammila.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
}
