package com.blog.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.payloads.JwtAuthRequest;
import com.blog.apis.payloads.JwtAuthResponse;
import com.blog.apis.security.JwtHelper;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
		try {
			System.out.println("request come here in login url");
			this.authenticate(request.getUsername(),request.getPassword());
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
			
			System.out.println(" this is userdtails ::"+userDetails);
			
			String token = this.helper.generateToken(userDetails);
			
			System.out.println(token);
			
			JwtAuthResponse authResponse = new JwtAuthResponse();
			authResponse.setToken(token);
			return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
	        // Your existing code here
	    } catch (Exception e) {
	        e.printStackTrace(); // or log the exception
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
		
	}

	private void authenticate(String username, String password) {
		System.out.println("come here in authenticate");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		this.authenticationManager.authenticate(authenticationToken);			
		
		
	
	}
}
