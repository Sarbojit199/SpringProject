package com.code.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entities.Category;
import com.code.entities.Product;
import com.code.entities.User;
import com.code.exception.ResourseNotFoundException;
import com.code.payload.CategoryDto;
import com.code.payload.ProductDto;
import com.code.repositories.CategoryRepo;
import com.code.repositories.ProductRepo;
import com.code.repositories.UserRepo;
import com.code.services.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ProductDto createProduct(ProductDto productDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("user", "user id", userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category", "category id", categoryId));
		
		Product product =this.modelMapper.map(productDto, Product.class);
		product.setAddedDate(new Date());
		product.setUser(user);
		product.setCategory(category);
		Product newPost = this.productRepo.save(product);
		return this.modelMapper.map(newPost, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
	Product product=this.productRepo.findById(productId).orElseThrow(()-> new ResourseNotFoundException("product", "product id", productId));
	product.setTitle(productDto.getTitle());
	product.setContent(productDto.getContent());
	Product updatedPost =this.productRepo.save(product);
		return this.modelMapper.map(updatedPost, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
	Product product =this.productRepo.findById(productId).orElseThrow(()->new ResourseNotFoundException("product", "product Id", productId));
	this.productRepo.delete(product);
		
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> allproducts=this.productRepo.findAll();
		List<ProductDto> productDtos =	allproducts.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		return productDtos;
		
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product=this.productRepo.findById(productId).orElseThrow(()->new ResourseNotFoundException("product", "product id", productId));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getProductByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("category", "category id", categoryId));
		List<Product> products = this.productRepo.findBycategory(cat);
		List<ProductDto> productDtos = products.stream().map((product)->this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
		
		return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
	User user =this.userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("user", "userId", userId));
	List<Product> products= this.productRepo.findByUser(user);
	
List<ProductDto> productDtos=	products.stream().map((product)->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<Product> searchProducts(String keyword) {
		
		return null;
	}

}
