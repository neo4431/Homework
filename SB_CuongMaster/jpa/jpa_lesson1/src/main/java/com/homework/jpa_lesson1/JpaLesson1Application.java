package com.homework.jpa_lesson1;

import com.homework.jpa_lesson1.service.CustomerServiceI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaLesson1Application implements CommandLineRunner {
	public static final Logger LOGGER = LoggerFactory.getLogger(JpaLesson1Application.class);

	@Autowired
	CustomerServiceI service;

	public static void main(String[] args) {
		SpringApplication.run(JpaLesson1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// List<Customer> customers = service.findAll();
		// Sắp xếp theo các trường (VD với Email)
		System.out.println("========Order by email=========");
		service.orderByEmail().forEach(customer -> {
			LOGGER.info(customer.getId() + " - " + customer.getEmail());
		});

		// Tìm kiếm customer có firstname hoặc lastname chứa keyword
		System.out.println("========find by keyword=========");
		service.findByKeyword("im").forEach(customer -> {
			LOGGER.info(customer.toString());
		});
	}
}
