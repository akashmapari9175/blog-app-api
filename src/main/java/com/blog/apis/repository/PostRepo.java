package com.blog.apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.apis.entitiy.Category;
import com.blog.apis.entitiy.Post;
import com.blog.apis.entitiy.User;
import com.blog.apis.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	Post save(Post post);
	
	List<Post> findByTitleContaining(String title);
	List<Post> findByContentContaining(String title);
	
	//this is a manully writing a query 
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
}
