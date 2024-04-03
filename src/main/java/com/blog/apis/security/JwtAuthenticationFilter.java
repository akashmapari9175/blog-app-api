package com.blog.apis.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		1. get token
		
		String requesttoken = request.getHeader("Authorization");
		
		//Bearer 12n@43$53....
		
		System.out.println("request token :"+requesttoken);
		
		String username = null;
		String token = null;
		
		if(requesttoken !=null && requesttoken.startsWith("Bearer"))
		{
			token = requesttoken.substring(7);
			
			
			try {
				username  = this.jwtTokenHelper.extractUsername(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
			}catch(MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
			
		}
		else {
			System.out.println("jwt token not being with Bearer");
		}
		 
		//once we get the token , now validate
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		
		System.out.println(userDetails);
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			if(this.jwtTokenHelper.validateToken(token, userDetails))//that means tokken are valid
			{
				//changa chal raha hai aab authentication karna hai
				UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				passwordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//now set the authentication in security
				SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
				
			}
			else {
				System.out.println("Invalid jwt token");
			}	
		}
		else {
			System.out.println("username is null or context is not null");
		}
		filterChain.doFilter(request, response);
	}

	
	
}
