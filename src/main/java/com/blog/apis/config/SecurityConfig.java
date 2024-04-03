package com.blog.apis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.apis.security.JwtAuthenticationEntryPoint;
import com.blog.apis.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

	
//	@Bean
//	public UserDetailsService getDetailsSerive() {
//		
//		return new UserDetailsServiceImpl();
//	}
//	
//	@Autowired
//	private JwtAuthenticationEntryPoint authenticationEntryPoint;
//	
//	@Autowired
//	private JwtAuthenticationFilter authenticationFilter;
//	
	
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(getDetailsSerive());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////           http
//                .csrf(e->e.disable())
//                .authorizeHttpRequests(authorize -> authorize
//            			.anyRequest().authenticated()
//            		)
//                .exceptionHandling(handling -> handling.authenticationEntryPoint(this.authenticationEntryPoint))
//                .sessionManagement(management -> management
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
//		http.addFilterBefore(this.authenticationFilter,UsernamePasswordAuthenticationFilter.class);
////		return http.build();
//		 return http.csrf().disable()
//		            .authorizeHttpRequests()
//		            .and()
//		            .sessionManagement()
//		            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		            .and()
//		            .authenticationProvider(authenticationProvider())
//		            .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
//
//	}
	
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
	
//	  @Autowired
//	    private JwtAuthenticationEntryPoint point;
//	    @Autowired
//	    private JwtAuthenticationFilter filter;
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    	
//	    	 http
//	    			.csrf(AbstractHttpConfigurer::disable)
//	    			.authorizeHttpRequests(
//	    					req->req.requestMatchers("/login/**","/register/**").permitAll()
//	    					.anyRequest()
//	    					.authenticated()
//	    					);
//	    	http.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//	    			         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//	        return http.build();
////	    }
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
	    	System.out.println("rewust security come");
	    	http
	    	.csrf(csrf->csrf.disable())
	    	.authorizeHttpRequests(req -> req.requestMatchers("/api/login").permitAll()
	    			.anyRequest().authenticated()
	    			);
	    	 
	    	http.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
	         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
              
	    	http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    	
	    	return http.build();
	    }
	    
	    
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	    	auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	    }
	    
	    
	    @Bean
		public BCryptPasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
	    
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	    
//	    @Bean
//	    public AuthenticationManager authenticationManagerBean() throws Exception{
//	    	return  super.authenticationManagerBean();
//	    }
	    
	
}
