package io.rammila.api.cache;

import io.rammila.api.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ProductCache {
/*
    @Cacheable(value = "productCache",key="#userId",condition="#userId != null")
    public List<Product> getProductByUserId(UUID userId){
        return new ArrayList<>();
    }
*/

}
