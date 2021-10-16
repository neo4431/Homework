package com.homework.shopingcart.controller;

import com.homework.shopingcart.model.Cart;
import com.homework.shopingcart.repository.CartRepository;
import com.homework.shopingcart.repository.ProducRepo;
import com.homework.shopingcart.repository.ProducRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    // @Autowired
    // CartRepository repo;

    @Autowired
    ProducRepo repo;

    @Autowired
    ProducRepository productRepo;
    @GetMapping
    public ResponseEntity<?> getAll(){
        productRepo.initData();
        return ResponseEntity.ok(repo.findAll());
    }
}