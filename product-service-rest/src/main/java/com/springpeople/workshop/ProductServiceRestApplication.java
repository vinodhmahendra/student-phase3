package com.springpeople.workshop;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springpeople.workshop.domain.Product;
import com.springpeople.workshop.repository.ProductRepository;

@SpringBootApplication
public class ProductServiceRestApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product("HP Laptop ABC", new BigDecimal(45000.00));
		
		productRepository.save(p1);
	}

}
