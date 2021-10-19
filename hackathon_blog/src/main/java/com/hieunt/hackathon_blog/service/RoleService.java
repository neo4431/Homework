package com.hieunt.hackathon_blog.service;

import java.util.List;

import com.hieunt.hackathon_blog.entity.Role;
import com.hieunt.hackathon_blog.exception.ResourceNotFoundException;
import com.hieunt.hackathon_blog.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepo;

    public List<Role> findAll(){
        return roleRepo.findAll();
    }

    public Role findById(Integer id){
        return roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Khong tim thay quyen"));
    }
}
