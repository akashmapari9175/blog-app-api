package com.blog.apis.entitiy;

import java.util.Date;

import com.blog.apis.payloads.CategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title", length = 100,nullable = false)
	private String title;
	
	
	@Column(length = 10000)
	private String content;
	
	private String iamgeName;
	
	private Date addedDate;
	
	@ManyToOne  //many post have one category 
	@JoinColumn(name = "category_id") //change the name of the column
	private Category category;  //this is the join column 
	
	@ManyToOne
	private User user;

	
	
}
