package com.code.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.Category;
import com.code.entities.Product;
import com.code.entities.User;

public interface ProductRepo extends JpaRepository<Product,Integer>{
	List<Product> findByUser(User user);
	List<Product> findBycategory(Category category);

}
