package com.hcl.service;

import com.hcl.model.Comment;
import com.hcl.payload.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);

}
