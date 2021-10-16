package com.homework.shopingcart.repository;

import com.homework.shopingcart.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProducRepo extends JpaRepository<Product,Long>{
    
}