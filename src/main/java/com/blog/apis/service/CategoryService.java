package com.blog.apis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.apis.payloads.CategoryDto;

@Service
public interface CategoryService {

	//create 
	public CategoryDto createCategory(CategoryDto user);
	
	//update
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId);
	
	//get by id
	public CategoryDto getCategoryById(Integer categoryId);
	
	//get all
	public List<CategoryDto> getAllCategory();
	
	//delete
	public void deleteCategory(Integer categoryId);
}
