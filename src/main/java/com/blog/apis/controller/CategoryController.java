package com.blog.apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.payloads.CategoryDto;
import com.blog.apis.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/save")
	public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.service.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.OK);
	}
	
	@PutMapping("/get-category/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer id) {
		CategoryDto updateCategory = this.service.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.CREATED);
	}
	
	@GetMapping("/get-category/{categoryId}")
	public CategoryDto getCategoryById(@PathVariable("categoryId") Integer id)
	{
		CategoryDto categoryById = this.service.getCategoryById(id);
		return categoryById;
	}
	
	@GetMapping("/get-categorys")
	public List<CategoryDto> getAllCategory(){
		List<CategoryDto> allCategory = this.service.getAllCategory();
		return allCategory;
		
	}
	
	
}
