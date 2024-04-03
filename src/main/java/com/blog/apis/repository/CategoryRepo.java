package com.blog.apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.apis.entitiy.Category;
import com.blog.apis.payloads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	Category save(CategoryDto category);

	//void save(CategoryDto category);

	
}
