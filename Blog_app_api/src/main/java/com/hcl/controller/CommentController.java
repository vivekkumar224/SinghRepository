package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.payload.ApiResponse;
import com.hcl.payload.CategoryDto;
import com.hcl.payload.CommentDto;
import com.hcl.service.CommentService;



@RestController
@RequestMapping("/apis/cpmment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	//postMapping
		@PostMapping("/addcomment/{postId}")
		public ResponseEntity<CommentDto> addComment(@javax.validation.Valid @RequestBody CommentDto commentDto,@PathVariable Integer postId)
		{
			CommentDto comment = commentService.createComment(commentDto, postId);
			
			return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
			
		}
		
		@DeleteMapping("/deleteComment/{commentId}")
		public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer commentId)
		{
			this.commentService.deleteComment(commentId);
			
			return new ResponseEntity<ApiResponse>(new ApiResponse("Data Deleted Succesfully", true),HttpStatus.OK);
		}

}
