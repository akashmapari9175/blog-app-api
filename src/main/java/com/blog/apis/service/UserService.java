package com.blog.apis.service;

import java.util.List;

import com.blog.apis.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updatUser(UserDto user,Integer userId);
	UserDto getById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
	
}
