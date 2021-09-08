package com.homework.jpa_lesson1.repository;

import com.homework.jpa_lesson1.entity.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}