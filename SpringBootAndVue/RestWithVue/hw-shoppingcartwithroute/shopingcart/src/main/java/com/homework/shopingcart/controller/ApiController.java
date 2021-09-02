package com.homework.shopingcart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.homework.shopingcart.model.Cart;
import com.homework.shopingcart.model.Product;
import com.homework.shopingcart.model.User;
import com.homework.shopingcart.repository.ProductRepository;
import com.homework.shopingcart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAll(HttpSession session){
        Map<String, Object> response = new HashMap<>();
        response.put("count", cartService.countItemInCart(session));
        response.put("list", productRepository.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> GetListSearch(@RequestParam("keyword") String keyword, @RequestParam("sort") String sort){
        return ResponseEntity.ok(productRepository.searchByKeyword(keyword, sort));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id){
        return ResponseEntity.ok(productRepository.findById(id).get());
    }

    @PostMapping(value="/add-to-cart/{id}")
    public ResponseEntity<Integer> addToCart(@PathVariable Long id, HttpSession session) {
        cartService.addToCart(id, session);
        Integer response = cartService.countItemInCart(session);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart(HttpSession session){
        return ResponseEntity.ok(cartService.getCart(session));
    }

    @PatchMapping("/cart/decrease/{id}")
    public ResponseEntity<?> decreaseToCart(@PathVariable("id") Long id,HttpSession session){
        cartService.decreaseToCart(id, session);
        Map<String, Object> response = new HashMap<>();
        response.put("count", cartService.countItemInCart(session));
        response.put("cart", cartService.getCart(session));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/cart/increase/{id}")
    public ResponseEntity<?> increaseToCart(@PathVariable("id") Long id,HttpSession session){
        cartService.addToCart(id, session);
        Map<String, Object> response = new HashMap<>();
        response.put("count", cartService.countItemInCart(session));
        response.put("cart", cartService.getCart(session));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-out")
    public ResponseEntity<?> checkOut(@Valid @RequestBody User user, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(bindingResult.getAllErrors()); 
        }
            session.invalidate();
            return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}