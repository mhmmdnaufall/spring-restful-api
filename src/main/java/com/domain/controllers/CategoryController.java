package com.domain.controllers;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.CategoryData;
import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.model.entities.Category;
import com.domain.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(
        @Valid @RequestBody CategoryData categoryData,
        Errors errors) {

        return insertToDatabase(categoryData, errors);

    }

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(
        @Valid @RequestBody CategoryData categoryData,
        Errors errors) {

        return insertToDatabase(categoryData, errors);
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByName(
        @RequestBody SearchData searchData,
        @PathVariable("size") int size,
        @PathVariable("page") int page) {
            
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findByName(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(
        @RequestBody SearchData searchData,
        @PathVariable("size") int size,
        @PathVariable("page") int page,
        @PathVariable("sort") String sort) {
            
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        if(sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }

        return categoryService.findByName(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(
        @RequestBody Category[] categories) {

        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        responseData.setStatus(true);

        return ResponseEntity.ok(responseData);

    }

    public ResponseEntity<ResponseData<Category>> insertToDatabase(
        CategoryData categoryData, 
        Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(var error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.ok(responseData);
    }


}