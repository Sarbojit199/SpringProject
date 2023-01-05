package com.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	
			
}
