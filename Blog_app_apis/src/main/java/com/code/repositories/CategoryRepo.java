package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
