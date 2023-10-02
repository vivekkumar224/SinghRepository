package com.hcl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hcl.exception.ResourceNotFoundException;
import com.hcl.model.Category;
import com.hcl.model.Post;
import com.hcl.model.User;
import com.hcl.payload.PostDto;
import com.hcl.payload.PostResponse;
import com.hcl.reporsitory.CategoryRepo;
import com.hcl.reporsitory.PostRepo;
import com.hcl.reporsitory.UserRepo;
import com.hcl.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto posDto,Integer userId,Integer categoryId) {
		
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
	    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

		Post post = this.modelMapper.map(posDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto UpdatePost(PostDto postDto, Integer id) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setAddedDate(new Date());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		this.postRepo.delete(post);


	}

	@Override
	public PostDto getPost(Integer id) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
		// TODO Auto-generated method stub
	
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
		Page<Post> allPosts= this.postRepo.findAll(p);
		List<Post> posts = allPosts.getContent();
		
		  List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		  
		  PostResponse postResponse=new PostResponse();
		  postResponse.setPosts(posDtos);
		  postResponse.setPageNumber(allPosts.getNumber());
		  postResponse.setPageSize(allPosts.getSize());
		  postResponse.setTotalElements(allPosts.getTotalElements());
		  postResponse.setLastpages(allPosts.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> getAllUPostbyUSer(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		List<Post> posts = this.postRepo.findByUser(user);
		  List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return posDtos;
	}

	@Override
	public List<PostDto> getAllPostbycategory(Integer id) {
		// TODO Auto-generated method stub
	     Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
	     List<Post> posts = this.postRepo.findByCategory(category);
	     List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return posDtos;
	}

	@Override
	public List<PostDto> searchPostByKeyboard(String keyboard) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postRepo.findByPostTitleContaining(keyboard);
	     List<PostDto> posDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return posDtos;
	}

}
