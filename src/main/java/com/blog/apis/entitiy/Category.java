package com.blog.apis.entitiy;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer categoryId;
	
	@Column(name = "title", length = 100,nullable = false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	//one category have a multiple post that why we use the List for storing the multiple post
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)  //child automatically save when parent is save 
	private List<Post> posts=new ArrayList<>();
	
}
