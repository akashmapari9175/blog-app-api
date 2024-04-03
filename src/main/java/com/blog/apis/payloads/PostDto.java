package com.blog.apis.payloads;

import java.util.Date;

import com.blog.apis.entitiy.Category;
import com.blog.apis.entitiy.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {

	
	private Integer id;
	private String title;
	private String content;
	private String iamgeName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	
}
