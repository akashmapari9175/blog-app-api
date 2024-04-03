package com.blog.apis.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.apis.fileservice.FileService;
import com.blog.apis.payloads.PostDto;
import com.blog.apis.payloads.PostResponce;
import com.blog.apis.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		 PostDto createPost = postService.createPost(postDto, userId, categoryId);
		 return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//return post
	@GetMapping("/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId)
	{
		List<PostDto> listDto = this.postService.getPostByUserId(userId);
		return new  ResponseEntity<List<PostDto>>(listDto,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId)
	{
		List<PostDto> listDto = this.postService.getPostByCategoryId(categoryId);
		return new  ResponseEntity<List<PostDto>>(listDto,HttpStatus.OK);
	}
	
	@GetMapping("/getpost/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		PostDto post = this.postService.getPost(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	//get all post 
	@GetMapping("/posts")   //@reqestparam means we pass the value in the url ?name="aakash"
	public ResponseEntity<PostResponce>getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize
			){
		
		PostResponce postResponce = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
	}
	
	//delete post by id 
	@DeleteMapping("/delete/{postId}")
	public String deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		
		return postId+" : this post deleted successfully";
	}
	
	//search here url
	@GetMapping("/posts/search/{keywords}") //this is work only serach by the title of the post
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords
			){
	
		List<PostDto> serarchPosts = this.postService.serarchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(serarchPosts,HttpStatus.OK);
	}
	
	//post image upload is here
	@PostMapping("/post/image/upload/{postId}" )
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		PostDto post = this.postService.getPost(postId);
		 String path12 = path.trim(); // Trim leading and trailing whitespace
		String fileName = this.fileService.updoadImage(path12, image);
		System.out.println(fileName);
		post.setIamgeName(fileName);
		System.out.println(post.toString());
		PostDto updatePost = this.postService.updatePost(post,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
}