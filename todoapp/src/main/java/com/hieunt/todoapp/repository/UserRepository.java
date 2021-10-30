package com.hieunt.todoapp.repository;

import java.util.Optional;

import com.hieunt.todoapp.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}