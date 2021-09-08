package com.homework.jpa_lesson1.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.homework.jpa_lesson1.entity.Customer;
import com.homework.jpa_lesson1.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceI implements CustomerService {
    @Autowired
    CustomerRepository repo;

    @Override
    public List<Customer> findAll() {
        // TODO Auto-generated method stub
        List<Customer> customers = new ArrayList<>();
        repo.findAll().forEach(customer -> {
            customers.add(customer);
        });
        return customers;
    }
    
    @Override
    public List<Customer> findByKeyword(String keyword) {
        String keywordToLowerCase = keyword.toLowerCase();
        return this.findAll().stream().filter(customer -> {
            return customer.getFirstName().toLowerCase().contains(keywordToLowerCase)
                    || customer.getLastName().toLowerCase().contains(keywordToLowerCase);
        }).collect(Collectors.toList());
    }

    @Override
    public List<Customer> orderByFirstName() {
        // TODO Auto-generated method stub
        return this.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getFirstName))
        .collect(Collectors.toList());
    }

    @Override
    public List<Customer> orderByLastName() {
        // TODO Auto-generated method stub
        return this.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getLastName))
        .collect(Collectors.toList());
    }

    @Override
    public List<Customer> orderByEmail() {
        // TODO Auto-generated method stub
        return this.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getEmail))
        .collect(Collectors.toList());
    }

    @Override
    public List<Customer> orderByMobile() {
        // TODO Auto-generated method stub
        return this.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getMobile))
        .collect(Collectors.toList());
    }

    @Override
    public List<Customer> orderByJob() {
        // TODO Auto-generated method stub
        return this.findAll()
        .stream()
        .sorted(Comparator.comparing(Customer::getJob))
        .collect(Collectors.toList());
    }
}