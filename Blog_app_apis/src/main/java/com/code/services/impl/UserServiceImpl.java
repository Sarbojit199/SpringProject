package com.code.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.entities.User;
import com.code.exception.ResourseNotFoundException;
import com.code.payload.UserDto;
import com.code.repositories.UserRepo;
import com.code.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser= this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userToDto1= this.userToDto(updatedUser);
		return userToDto1;
	}

	public UserDto getUserById(Integer userId) {
	User user=this.userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	public void deleteUser(Integer UserId) {
		
		User user= this.userRepo.findById(UserId).orElseThrow(()->new ResourseNotFoundException("User","Id", UserId));
		this.userRepo.delete(user);
		
	}
public User dtoToUser(UserDto userDto) {
	
	User user=this.modelMapper.map(userDto, User.class);
//	User user=new User();
//	user.setId(userDto.getId());
//	user.setName(userDto.getName());
//	user.setEmail(userDto.getEmail());
//	user.setAbout(userDto.getAbout());
//	user.setPassword(userDto.getPassword());
	return user;
	
}

public UserDto userToDto(User user) {
	UserDto userDto=this.modelMapper.map(user, UserDto.class);
//	UserDto userDto=new UserDto();
//	userDto.setId(user.getId());
//	userDto.setName(user.getName());
//	userDto.setEmail(user.getEmail());
//	userDto.setAbout(user.getAbout());
//	userDto.setPassword(user.getPassword());
	return userDto;
}
}
