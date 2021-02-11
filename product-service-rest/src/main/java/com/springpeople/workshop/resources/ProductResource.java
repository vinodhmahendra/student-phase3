package com.springpeople.workshop.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springpeople.workshop.domain.Product;
import com.springpeople.workshop.repository.ProductRepository;

@RestController
public class ProductResource {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> retrieveAllSProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product retrieveProduct(@PathVariable Integer id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get();
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productRepository.deleteById(id);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		Product savedProduct = productRepository.save(product);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody  Product product, @PathVariable Integer id) {

		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent())
			return ResponseEntity.notFound().build();

		
		Product _product = productOptional.get();
		
		_product.setName(product.getName());
		_product.setPrice(product.getPrice());

		return ResponseEntity.noContent().build();
	}
}
