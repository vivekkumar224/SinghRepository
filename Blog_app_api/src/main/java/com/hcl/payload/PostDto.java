package com.hcl.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hcl.model.Category;
import com.hcl.model.User;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	
	
	private Integer postId;
	private String  postTitle;
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto  user;
	
	private Set<CommentDto> comments=new HashSet<CommentDto>();

	

}
