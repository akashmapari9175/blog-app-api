package com.blog.apis.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.apis.entitiy.Category;
import com.blog.apis.entitiy.Post;
import com.blog.apis.entitiy.User;
import com.blog.apis.exceptions.UserNotFoundException;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponce;
import com.blog.apis.repository.CategoryRepo;
import com.blog.apis.repository.PostRepo;
import com.blog.apis.repository.UserRepo;
import com.blog.apis.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto dto,Integer userId,Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new UserNotFoundException(categoryId));
		
		
		Post post = this.mapper.map(dto, Post.class);
		post.setIamgeName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
	
		return this.mapper.map(newPost, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new UserNotFoundException(postId));
		post.setAddedDate(dto.getAddedDate());
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		post.setIamgeName(dto.getIamgeName());
		Post postnew = postRepo.save(post);
	    return this.mapper.map(postnew,PostDto.class);
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new UserNotFoundException(postId));
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize) {
		
	    Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize);  //this is an pageble object return the data on that page
	    System.out.println(p);
		
		Page<Post> pagePost = this.postRepo.findAll(p);   //return data from database for the 
		List<Post> posts = pagePost.getContent();  //all data are here than convert page into list  
		
		//find data one by one and convet into the postdto 
		List<PostDto> postDtos = posts.stream().map(post->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponce postResponce = new PostResponce();
		
		postResponce.setContent(postDtos);
		postResponce.setPageNumber(pagePost.getNumber());
		postResponce.setPageSize(pagePost.getSize());
		postResponce.setTotalElement(pagePost.getTotalElements());
		postResponce.setTotalPage(pagePost.getTotalPages());
		postResponce.setLastPage(pagePost.isLast());
		
		
		
		return postResponce;
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.deleteById(postId);
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtoList = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtoList;
		
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer categoryId) 
	{
		
		//this.categoryRepo.findById(categoryId).orElseThrow(()->);
		Category catgory = categoryRepo.findById(categoryId).orElseThrow(()->new UserNotFoundException(categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(catgory);
		 
		List<PostDto> postDtoList = posts.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtoList;
		
	}

	@Override
	public List<PostDto> serarchPosts(String keyword) 
	{
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		//posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	
		List<PostDto> postDtos = posts.stream().map(post->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
