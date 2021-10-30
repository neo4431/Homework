package com.hieunt.todoapp.controller;

import com.hieunt.todoapp.entity.User;
import com.hieunt.todoapp.entity.UserDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/sign-up")
    public String getFormCreateAccount(Model model){
        model.addAttribute("user", new UserDto());
        return "create_form";
    }

    @PostMapping("/account/create")
    public String addUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
                model.addAttribute("messageError", "Password Khong giong nhau");
            }
            model.addAttribute("user", userDto);
            return "create_form";
        }
        return "index";
    }
}