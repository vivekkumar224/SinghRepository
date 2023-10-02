package com.hcl.service;

import java.util.List;

import com.hcl.model.Post;
import com.hcl.payload.PostDto;
import com.hcl.payload.PostResponse;

public interface PostService {
	
	
	//create 
	
	PostDto createPost(PostDto posDto,Integer userId,Integer categoryId);
	
	
	//update 
	
	PostDto UpdatePost(PostDto postDto,Integer id);
	
	//delete
	
	void deletePost(Integer id);
	
	
	//get
	
	PostDto getPost(Integer id);
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	
	//get all post by user
	
	List<PostDto> getAllUPostbyUSer(Integer id);
	
	//get All post by Category
	
	List<PostDto> getAllPostbycategory(Integer id);
	
	//search post ny keyboard
	
	List<PostDto> searchPostByKeyboard(String keyboard);

}
