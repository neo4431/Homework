package com.hieunt.hackathon_blog.repository;

import java.util.Optional;

import com.hieunt.hackathon_blog.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);
}