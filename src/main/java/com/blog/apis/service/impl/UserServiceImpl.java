package com.blog.apis.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.apis.entitiy.User;
import com.blog.apis.exceptions.UserNotFoundException;
import com.blog.apis.payloads.UserDto;
import com.blog.apis.repository.UserRepo;
import com.blog.apis.service.UserService;

@Service  //this is must
public class UserServiceImpl  implements UserService {

	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	private PasswordEncoder encoder;	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);
		//save the user for that we need the user object  
		//userDto.setPassword(encoder.encode(userDto.getPassword()));
		User save = userRepo.save(user);
		System.out.println(userDto.getPassword());
		//and we have to return the userDto for that we have to convet to the useDto object 
		return userToUserDto(save);
	}

	@Override
	public UserDto updatUser(UserDto userDto, Integer userId) {
		//User user = this.userDtoToUser(userDto);  //this return the user object 
		User user = userRepo.findById(userId).get();
		user.setId(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User save = userRepo.save(user);
		return userToUserDto(save);
		
		//my logic first find the user by the userid than set the values all in the user than convett the uesr into the userDto object than return 
	}

	@Override
	public UserDto getById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
		
		return  userToUserDto(user);  
		//my logic    is to find the user by  userId than return the userDto object
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = userRepo.findAll();
        List<UserDto> userDto = users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
        return userDto ;  //this is the way to convert user list to the userDto list 
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepo.deleteById(userId);
	}
	
	// here i am set the data userDto to the user we are not directly intract with the entitiy
	public User userDtoToUser(UserDto userDto) {  //lombok working now for that we have to set the lombok jar in the sts ide
		User user = new User();
		user.setId(userDto.getId()); 
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	//now i have to set the data uesr to the userDto becaouse first data come into the user from userrepo
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
	
	
	

}
