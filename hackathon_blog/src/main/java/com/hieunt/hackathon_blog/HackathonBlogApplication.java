package com.hieunt.hackathon_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HackathonBlogApplication{

	public static void main(String[] args) {
		SpringApplication.run(HackathonBlogApplication.class, args);

		BCryptPasswordEncoder a = new BCryptPasswordEncoder();
		for (int i = 0; i < 3; i++) {
			System.out.println(a.encode("123")); 
		}
		
	}
}
