package com.hieunt.todoapp.controller;

import com.hieunt.todoapp.entity.Project;
import com.hieunt.todoapp.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping()
    public String getProjectsPage(){
        return "projects";
    }

    @GetMapping("/create")
    public String getFormCreateOrUpdateProject(Model model){
        model.addAttribute("project", new Project());
        return "project_form";
    }

    @PostMapping("/create-update")
    public String addOrUpdateProject(@ModelAttribute Project project){
        return "redirect:/projects";
    }
}