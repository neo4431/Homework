package com.hieunt.hackathon_blog.service;

import java.util.List;

import com.hieunt.hackathon_blog.entity.User;
import com.hieunt.hackathon_blog.exception.ResourceNotFoundException;
import com.hieunt.hackathon_blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Resource not found!!!"));
    }

    public User findById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found!!!"));
    }

    public void save(User user){
        if(user.getId() == null){
            userRepository.save(user);
        } else {
            User userUpdate = this.findById(user.getId()); 
            userUpdate.setEmail(user.getEmail());
            userUpdate.setFullName(user.getFullName());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setRoles(user.getRoles());
            userUpdate.setComments(user.getComments());
            userUpdate.setPosts(user.getPosts());
        }
    }

    public void deleteById(Integer id){
        User user = this.findById(id);
        userRepository.delete(user);
    }

    
}