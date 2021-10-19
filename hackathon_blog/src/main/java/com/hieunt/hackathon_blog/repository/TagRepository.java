package com.hieunt.hackathon_blog.repository;

import com.hieunt.hackathon_blog.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer>{
    
}
