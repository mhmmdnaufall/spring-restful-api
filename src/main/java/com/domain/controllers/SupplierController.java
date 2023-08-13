package com.domain.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierData;
import com.domain.model.entities.Supplier;
import com.domain.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(
        @Valid @RequestBody SupplierData supplierData,
        Errors errors) {

        return insertToDatabase(supplierData, errors);

    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findall();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(
        @Valid @RequestBody SupplierData supplierData,
        Errors errors) {

        return insertToDatabase(supplierData, errors);

    }

    @PostMapping("/search/byemail")
    public Supplier findByEmail(@RequestBody SearchData searchData) {
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/byname")
    public List<Supplier> findByName(@RequestBody SearchData searchData) {
        return supplierService.findByName(searchData.getSearchKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Supplier> findByNameStartWith(@RequestBody SearchData searchData) {
        return supplierService.findByNameStartWith(searchData.getSearchKey());
    }

    @PostMapping("/search/nameoremail")
    public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData) {
        return supplierService.findByNameOrEmail(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }


    public ResponseEntity<ResponseData<Supplier>> insertToDatabase(
        SupplierData supplierData, 
        Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(var error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Tidak pakai library model mapper
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierData.getName());
        // supplier.setAddress(supplierData.getAddress());
        // supplier.setEmail(supplierData.getEmail());

        // Pake library model mapper
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        // cara kerjanya dgn melihat nama field yang sama

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

}
