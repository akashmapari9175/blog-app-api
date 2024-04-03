package com.blog.apis.payloads;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	
	private int id;
	
	@NotEmpty(message="name should not be emply in this filed")
	@Size(max = 8, min = 2)
	@Column(unique =true)
	private String name;
	
	@NotEmpty(message="email should not be empty")
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "password should not be empty")
	@Size(min = 3, max=10,message="password should be in the between 3 and 10")
	private String password;
	
	@NotEmpty(message = "write some thing here")
	private String about;
}
