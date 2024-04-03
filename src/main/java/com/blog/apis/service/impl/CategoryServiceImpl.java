package com.blog.apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.apis.entitiy.Category;
import com.blog.apis.exceptions.UserNotFoundException;
import com.blog.apis.payloads.CategoryDto;
import com.blog.apis.payloads.UserDto;
import com.blog.apis.repository.CategoryRepo;
import com.blog.apis.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.categoryDtoToCategory(categoryDto);
		Category save = repo.save(category);
		return categoryToCategoryDto(save);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto,Integer id) {
		Category cat = repo.findById(id).get();
		cat.setCategoryDescription(catDto.getCategoryDescription());
		cat.setCategoryTitle(catDto.getCategoryTitle());
		
		Category save = repo.save(cat);
		return categoryToCategoryDto(save);
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = repo.findById(categoryId).orElseThrow(()->new UserNotFoundException(categoryId));
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> cat = repo.findAll();
		List<CategoryDto> catDto = cat.stream().map( cateach ->this.categoryToCategoryDto(cateach)).collect(Collectors.toList());
		return catDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		repo.deleteById(categoryId);
	}

	//convert categoryDto to category
	//we can done this using model mapper for that we have to put modelmapper dependency in pom.xml file
	//and create the method model mapper map with two argument 
	public Category categoryDtoToCategory(CategoryDto categoryDto)
	{
		Category cat = this.modelMapper.map(categoryDto,Category.class);
		//main file create bean go to there
		
		
		/*
		 * Category cat = new Category();
		 * cat.setCategoryId(categoryDto.getCategoryId());
		 * cat.setCategoryTitle(categoryDto.getCategoryTitle());
		 * cat.setCategoryDescription(categoryDto.getCategoryDescription());
		 */
		return cat;
	}
	
	//convert category to CategoryDto
    public CategoryDto categoryToCategoryDto(Category category) {
    	CategoryDto catDto = new CategoryDto();
    	catDto.setCategoryId(category.getCategoryId());
    	catDto.setCategoryDescription(category.getCategoryDescription());
    	catDto.setCategoryTitle(category.getCategoryTitle());
    	return catDto;
    }
	
}
