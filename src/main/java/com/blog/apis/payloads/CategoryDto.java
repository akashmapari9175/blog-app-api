package com.blog.apis.payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
}
