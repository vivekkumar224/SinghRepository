package com.hcl.service;

import java.util.List;


import com.hcl.payload.CategoryDto;

public interface CategoryService {
	
	//add
	public CategoryDto addCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	//delete
	public void deleteCategory(Integer id);
	//get
	public CategoryDto getCategory(Integer id);
	//getAll
	public List<CategoryDto> getAllCategories();

}
