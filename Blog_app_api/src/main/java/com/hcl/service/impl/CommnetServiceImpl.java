package com.hcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.exception.ResourceNotFoundException;
import com.hcl.model.Comment;
import com.hcl.model.Post;
import com.hcl.payload.CommentDto;
import com.hcl.reporsitory.CommentRepo;
import com.hcl.reporsitory.PostRepo;
import com.hcl.service.CommentService;
@Service
public class CommnetServiceImpl implements CommentService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper ModelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		Comment comment = this.ModelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		this.commentRepo.save(comment);
		return this.ModelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		 Comment comment= commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
		 this.commentRepo.delete(comment);
		// TODO Auto-generated method stub
		
	}

}
