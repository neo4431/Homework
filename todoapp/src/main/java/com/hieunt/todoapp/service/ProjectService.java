package com.hieunt.todoapp.service;

import java.util.List;

import com.hieunt.todoapp.entity.Project;
import com.hieunt.todoapp.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepo;

    public Project findById(Integer id){
        return projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay project"));
    }

    public List<Project> findAll(){
        return projectRepo.findAll();
    }

    public void save(Project project){
        if(project.getId() == null){
            projectRepo.save(project);
        } else {
            Project projectUpdate = this.findById(project.getId());
            projectUpdate.setProjectName(project.getProjectName());
            projectUpdate.setDescription(project.getDescription());
            projectUpdate.setStartDate(project.getStartDate());
            projectUpdate.setEndDate(project.getEndDate());
            projectRepo.save(projectUpdate);
        }
    }

    public void deleteById(Integer id){
        projectRepo.delete(this.findById(id));
    }
}