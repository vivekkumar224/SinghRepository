package com.hcl.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PostResponse {
	
	private List<PostDto> posts;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElements;
	
	private boolean lastpages;

}
