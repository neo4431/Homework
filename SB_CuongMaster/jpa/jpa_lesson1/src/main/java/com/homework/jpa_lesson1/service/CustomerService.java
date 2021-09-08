package com.homework.jpa_lesson1.service;

import java.util.List;

import com.homework.jpa_lesson1.entity.Customer;

public interface CustomerService {
    
    public List<Customer> findAll();

    public List<Customer> orderByFirstName();

    public List<Customer> orderByLastName();

    public List<Customer> orderByEmail();

    public List<Customer> orderByMobile();

    public List<Customer> orderByJob();

    public List<Customer> findByKeyword(String keyword);
}