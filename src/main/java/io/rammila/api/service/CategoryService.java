package io.rammila.api.service;

import io.rammila.api.model.Category;
import io.rammila.api.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
            log.info("fetching all  category ");
        return categoryRepository.findAll();
    }
}
