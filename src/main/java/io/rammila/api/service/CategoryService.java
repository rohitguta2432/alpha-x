package io.rammila.api.service;

import io.rammila.api.model.Category;
import io.rammila.api.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable("categorys")
    public List<Category> getAllCategory(){
            log.info("fetching all  category ");
        return categoryRepository.findAll();
    }
    public Category save(Category category){
        log.info("persis category: {} ", category);
        return categoryRepository.save(category);
    }
}
