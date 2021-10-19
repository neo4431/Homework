package com.hieunt.hackathon_blog.controller;

import javax.validation.Valid;

import com.hieunt.hackathon_blog.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String getRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register_form";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "register_form";
        }
        return "redirect:/index";
    }
}
