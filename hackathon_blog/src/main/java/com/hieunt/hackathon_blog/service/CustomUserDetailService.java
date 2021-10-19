package com.hieunt.hackathon_blog.service;

import com.hieunt.hackathon_blog.entity.CustomUserDetail;
import com.hieunt.hackathon_blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return new CustomUserDetail(userRepo.findByEmail(arg0).orElseThrow(()-> new UsernameNotFoundException("Khong tim thay nguoi dung!!!")));
    }
    
}
