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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String getAll(Model model) {
        return "posts";
    }

    @GetMapping("/posts")
    public String getPosts(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String sort, @RequestParam(required = false) boolean isAsc, Principal principal){
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("username", user.getFullName());
        }
        Page<Post> posts = postService.findAllPostAndUser(page, sort, isAsc);
        model.addAttribute("currentPage", posts.getNumber() + 1);
        model.addAttribute("totalPage", posts.getTotalPages());
        model.addAttribute("posts", posts);
        return "posts";
    }
}