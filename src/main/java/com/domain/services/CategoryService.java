package com.domain.services;

import org.springframework.data.domain.Pageable;

import com.domain.model.entities.Category;

public interface CategoryService {
    
    Category save(Category category);

    Category findOne(Long id);

    Iterable<Category> findAll();

    void removeOne(Long id);

    Iterable<Category> findByName(String name, Pageable pageable);

    Iterable<Category> saveBatch(Iterable<Category> categories);

}
