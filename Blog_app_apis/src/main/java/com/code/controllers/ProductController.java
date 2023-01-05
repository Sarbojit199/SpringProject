package com.code.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.entities.Product;
import com.code.payload.ProductDto;
import com.code.services.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(
			@RequestBody ProductDto productDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		ProductDto createProduct =this.productService.createProduct(productDto, userId, categoryId);
		return new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
	}
	@GetMapping("/user/{userId}/products")
	public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Integer userId){
		
	List<ProductDto> products= 	this.productService.getProductsByUser(userId);
	
	return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/products")
	public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Integer categoryId){
		
	List<ProductDto> products= 	this.productService.getProductByCategory(categoryId);
	
	return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	
	//get product detail
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		List<ProductDto> allProduct=this.productService.getAllProduct();
		return new ResponseEntity<List<ProductDto>>(allProduct,HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId){
		ProductDto productDto=this.productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable Integer productId){
		ProductDto updatedProduct =this.productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(updatedProduct,HttpStatus.OK);
	}
}
