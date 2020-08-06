package io.rammila.api.service;

import io.rammila.api.model.Product;
import io.rammila.api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(UUID productId){
        return productRepository.findById(productId).isPresent() ? productRepository.findById(productId).get() : null;
    }
}
