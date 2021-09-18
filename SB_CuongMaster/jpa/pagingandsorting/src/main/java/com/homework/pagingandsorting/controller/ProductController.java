package com.homework.pagingandsorting.controller;

import javax.validation.Valid;

import com.homework.pagingandsorting.entity.Product;
import com.homework.pagingandsorting.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping(value="/products")
    public String getIndex(@RequestParam(defaultValue = "1") int page, 
    @RequestParam(defaultValue = "id") String field, 
    @RequestParam(defaultValue = "asc") String order,
    Model model){
        Page<Product> products = service.getAllSortByField(page,field,order);
        model.addAttribute("products", products);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("totalItems", products.getTotalElements());
        model.addAttribute("currentPage", page);
        return "index";
    }

    @GetMapping("/add")
    public String getForm(Model model){
        model.addAttribute("title", "Create New Product");
        model.addAttribute("product", new Product());
        return "add_edit";
    }

    @GetMapping("/edit/{id}")
    public String getForm(@PathVariable Long id ,Model model){
        model.addAttribute("title", "Edit Product");
        model.addAttribute("product", service.findById(id));
        return "add_edit";
    }
    
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute Product product, 
    BindingResult bindingResult, 
    Model model, 
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "id") String field,
    @RequestParam(defaultValue = "asc") String order){
        if(bindingResult.hasErrors()){
            return "add_edit";
        }
        service.addOrUpdate(product);
        Page<Product> products = service.getAllSortByField(1,field,"asc");
        model.addAttribute("products", products);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("totalItems", products.getTotalElements());
        model.addAttribute("currentPage", page);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteById(id);
        return "redirect:/products";
    }
}