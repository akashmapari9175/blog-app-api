package com.blog.apis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blog.apis.entitiy.User;
import com.blog.apis.exceptions.UserNotFoundException;
import com.blog.apis.repository.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	private PasswordEncoder encoder;
//	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loding user from database
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new UserNotFoundException(100)); //i need to create this exception
		//user.setPassword(encoder.encode(user.getPassword()));
//		System.out.println(user.getPassword());
		System.out.println((user.getPassword()));
		System.out.println("user details mail "+ user.getEmail());
		return user;
		
		
	}

}
