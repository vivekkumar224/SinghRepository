package com.hcl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.payload.ApiResponse;
import com.hcl.payload.PostDto;
import com.hcl.payload.PostResponse;
import com.hcl.service.FileService;
import com.hcl.service.PostService;

import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/apis/")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;

	// post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = this.postService.UpdatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	@DeleteMapping("deletpost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("data deleted succesfully", true), HttpStatus.OK);
	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUSer(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getAllUPostbyUSer(userId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getAllPostbycategory(categoryId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable int postId) {
		PostDto post = this.postService.getPost(postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId") String sortBy) {
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keyword) {
		List<PostDto> posts = this.postService.searchPostByKeyboard(keyword);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//File Uploading
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImag(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException
	
	{
		PostDto post = this.postService.getPost(postId);
		String uploadImage = this.fileService.uploadImage(path, image);
		
		post.setImageName(uploadImage);
		PostDto updatePost = this.postService.UpdatePost(post, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping(value="/post/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imagename,HttpServletResponse response) throws IOException
	{
		InputStream resource = this.fileService.getResource(imagename, path);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
	

}
