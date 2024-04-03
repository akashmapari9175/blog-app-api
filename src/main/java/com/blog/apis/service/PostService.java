package com.blog.apis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.apis.entitiy.Post;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponce;

@Service
public interface PostService {

	//create 
	PostDto createPost(PostDto dto,Integer userId,Integer categoryId);
	
	//update post 
	PostDto updatePost(PostDto dto,Integer postId);
	
	//get post 
	PostDto getPost(Integer postId);
	
	//get all post
	PostResponce getAllPost(Integer pageNumber,Integer pageSize);
	
	//delete post
	void deletePost(Integer postId);
	
	//post by user id
	List<PostDto> getPostByUserId(Integer userId);  // by user have multiple post that why return list of post
	
	//post by category id
	List<PostDto> getPostByCategoryId(Integer categoryId);
	
	//search post
	List<PostDto> serarchPosts(String keyword);

	
}
