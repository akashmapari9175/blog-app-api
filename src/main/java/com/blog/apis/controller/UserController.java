package com.blog.apis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.entitiy.User;
import com.blog.apis.payloads.UserDto;
import com.blog.apis.repository.UserRepo;
import com.blog.apis.service.UserService;
import com.blog.apis.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService service;  //interface 
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private UserServiceImpl impl; 

	//post method - create the user 
	@PostMapping("/create-user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)  //@valid must without itwill get error 
	{
		UserDto createUser = this.service.createUser(userDto);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);	
	}
	
	//get mentod - all user 
	@GetMapping("/get-users")
	public List<UserDto> getAllUsers(){
		List<UserDto> allUser = this.service.getAllUser();
		return allUser;
	}
	
	//put method - update the existing users
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable int userId)
	{
		UserDto updatUser = this.service.updatUser(userDto,userId);
		/*
		 * UserDto user = this.service.getById(userId); user.setName(userDto.getName());
		 * user.setAbout(userDto.getAbout()); user.setPassword(userDto.getPassword());
		 * user.setEmail(userDto.getEmail());
		 * 
		 * User userSave = impl.userDtoToUser(user);
		 * 
		 * User save = this.repo.save(userSave);
		 */
		
		return ResponseEntity.ok(updatUser);
	}
	
	//delete method - user
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
		this.service.deleteUser(uid);
		//return "User Deleted Successfully";
	    return ResponseEntity.ok(Map.of("message","User Deleted Successfully"));  //this is the message of return 
	}
	
	
	
	
}
