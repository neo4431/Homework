package com.homework.pagingandsorting.service;

import com.homework.pagingandsorting.entity.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAll();
    Page<Product> getAllSortByField(int page, String field, String order);
    Product findById(Long id);
    void addOrUpdate(Product product);
    void deleteById(Long id);

}