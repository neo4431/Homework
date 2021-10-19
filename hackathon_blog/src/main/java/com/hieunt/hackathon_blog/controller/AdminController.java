package com.hieunt.hackathon_blog.controller;

import java.security.Principal;

import com.hieunt.hackathon_blog.entity.User;
import com.hieunt.hackathon_blog.service.RoleService;
import com.hieunt.hackathon_blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    public String getUsers(Model model, @RequestParam(defaultValue = "1") int page, Principal principal){
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("username", user.getFullName());
        }
        Page<User> users = userService.findAll(page);
        // model.addAttribute("currentPage", users.getNumber() + 1);
        // model.addAttribute("totalPage", users.getTotalPages());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "edit_role";
    }

    @PostMapping("/users/edit")
    public String performEdit(User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.findAll());
            return "edit_role";
        }

        if(user.getRoles().isEmpty()){
            user.addRole(roleService.findById(3));
        }
        userService.save(user);
        return "redirect:/admin/users";
    }
}
