package com.hieunt.hackathon_blog.controller;

import java.security.Principal;

import com.hieunt.hackathon_blog.entity.Post;
import com.hieunt.hackathon_blog.entity.User;
import com.hieunt.hackathon_blog.service.PostService;
import com.hieunt.hackathon_blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"/","/index"})
    public String getIndex(Model model, @RequestParam(defaultValue = "1") int page, Principal principal) {
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("username", user.getFullName());
        }
        Page<Post> posts = postService.findAll(page);
        model.addAttribute("currentPage", posts.getNumber() + 1);
        model.addAttribute("totalPage", posts.getTotalPages());
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/access-denied")
    public String getError403(){
        return "/error/403";
    }

}
