package com.code.services;

import java.util.List;

import com.code.entities.Product;
import com.code.payload.ProductDto;

public interface ProductService {
	
	
	ProductDto createProduct(ProductDto productDto,Integer userId,Integer categoryId);
	
	ProductDto updateProduct(ProductDto productDto,Integer productId);
	void deleteProduct(Integer productId);
	List<ProductDto> getAllProduct();
	ProductDto getProductById(Integer productId);
	
	
	List<ProductDto> getProductByCategory(Integer categoryId);
	
	List<ProductDto> getProductsByUser(Integer userId);
	
	List<Product> searchProducts(String keyword);
}
