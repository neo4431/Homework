package com.hieunt.hackathon_blog.repository;

import com.hieunt.hackathon_blog.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
