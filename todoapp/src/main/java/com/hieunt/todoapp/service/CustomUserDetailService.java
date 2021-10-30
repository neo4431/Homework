package com.hieunt.todoapp.service;

import com.hieunt.todoapp.entity.CustomUserDetails;
import com.hieunt.todoapp.entity.User;
import com.hieunt.todoapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Khong tim thay nguoi dung"));
        return new CustomUserDetails(user);
    }
    
}