package com.homework.shopingcart.repository;

import com.homework.shopingcart.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>{
    
}