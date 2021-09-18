package com.homework.pagingandsorting.service;

import java.util.Optional;

import com.homework.pagingandsorting.entity.Product;
import com.homework.pagingandsorting.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceI implements ProductService {
    @Autowired
    ProductRepository repo;

    @Override
    public Page<Product> getAll() {
        // TODO Auto-generated method stub
        return repo.findAll(PageRequest.of(0, 10));
    }

    @Override
    public Page<Product> getAllSortByField(int page, String field, String order) {
        // TODO Auto-generated method stub
        if(order.equals("desc")){
            return repo.findAll(PageRequest.of(page - 1, 10, Sort.by(field).descending()));
        }
        return repo.findAll(PageRequest.of(page - 1, 10, Sort.by(field)));
    }

    @Override
    public Product findById(Long id) {
        // TODO Auto-generated method stub
        return repo.findById(id).orElseThrow();
    }
    @Override
    public void addOrUpdate(Product newProduct) {
        // TODO Auto-generated method stub
        if(newProduct.getId() == null){
            repo.save(newProduct);
        } else {
            Product product = repo.findById(newProduct.getId()).get();
            product.setName(newProduct.getName());
            product.setBrand(newProduct.getBrand());
            product.setMadeIn(newProduct.getMadeIn());
            product.setPrice(newProduct.getPrice());
            repo.save(product);
        }
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            repo.delete(product.get());
        }
    }

}