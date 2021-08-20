package vn.trunghieunguyen.bookstore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    
    @GetMapping(value = "/")
    public String hello(){
        return "index";
    }
}