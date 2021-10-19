package com.hieunt.hackathon_blog.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;

import com.hieunt.hackathon_blog.entity.Comment;
import com.hieunt.hackathon_blog.entity.Post;
import com.hieunt.hackathon_blog.entity.User;
import com.hieunt.hackathon_blog.repository.TagRepository;
import com.hieunt.hackathon_blog.service.CommentService;
import com.hieunt.hackathon_blog.service.PostService;
import com.hieunt.hackathon_blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagRepository tagRepo;
    
    @GetMapping("/posts/{id}")
    public String getPost(Model model, @PathVariable int id, Principal principal){
        User user = userService.findByEmail(principal.getName());
        Post post = postService.findPostAndTagByPostId(id);
        String lastUpdate = new SimpleDateFormat("MM/dd/yyyy").format(post.getLastUpdate());
        model.addAttribute("user", user);
        model.addAttribute("formatDate", lastUpdate);
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "post_detail";
    }

    @PostMapping("/posts/{id}/add-comment")
    public String addComment(Principal principal, @PathVariable int id, @ModelAttribute Comment comment, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("comment", comment);
            return "post_detail";
        }
        // Post post = postService.findById(id);
        User user = userService.findByEmail(principal.getName());
        // user.addComment(comment);
        // post.addComment(comment);
        // postService.save(post);
        // userService.save(user);
        commentService.save(comment.getContent(), id, user.getId());
        return "redirect:/posts/" + id;
    }

    @GetMapping("post/edit/{id}")
    public String editPost(@PathVariable int id, Model model){
        Post post = postService.findPostAndTagByPostId(id);
        model.addAttribute("tags", tagRepo.findAll());
        model.addAttribute("post", post);
        return "edit_post";
    }

    @PostMapping("/edit-post")
    public String performEdit(@ModelAttribute Post post, BindingResult bindingResult, Model model, Principal principal){
        if(bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "edit_post";
        }
        User user = userService.findByEmail(principal.getName());
        postService.save(post, user.getId());
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable int id, Principal principal, Model model , @RequestParam(defaultValue = "1") int page){
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("username", user.getFullName());
        }
        Page<Post> posts = postService.findAll(page);
        model.addAttribute("currentPage", posts.getNumber() + 1);
        model.addAttribute("totalPage", posts.getTotalPages());
        model.addAttribute("posts", posts);
        postService.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/create-post")
    public String getFormCreatePost(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("tags", tagRepo.findAll());
        return "edit_post";
    }
}
