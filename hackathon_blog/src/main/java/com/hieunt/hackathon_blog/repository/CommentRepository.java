package com.hieunt.hackathon_blog.repository;

import com.hieunt.hackathon_blog.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}
