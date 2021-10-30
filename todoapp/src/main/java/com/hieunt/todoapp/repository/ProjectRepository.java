package com.hieunt.todoapp.repository;

import com.hieunt.todoapp.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}