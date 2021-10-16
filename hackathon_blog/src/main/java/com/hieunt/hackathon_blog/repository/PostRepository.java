package com.hieunt.hackathon_blog.repository;

import com.hieunt.hackathon_blog.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>{
    
}