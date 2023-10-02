package com.hcl.payload;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	@NotEmpty
	@Size(min = 4, message = "name must be minimum four character")
	private String categoryName;
	@NotEmpty
	@Size(min = 10, message = "Description must be minimum four character")
	private String categoryDescription;

	

}
