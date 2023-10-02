package com.hcl.service.impl;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.exception.ResourceNotFoundException;
import com.hcl.model.Category;
import com.hcl.payload.CategoryDto;
import com.hcl.reporsitory.CategoryRepo;
import com.hcl.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepo.save(category);
		CategoryDto savedCategoryDto = this.modelMapper.map(savedCategory, CategoryDto.class);
		return savedCategoryDto;
	}
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		// TODO Auto-generated method stub
	     Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
	     category.setCategoryName(categoryDto.getCategoryName());
	     category.setCategoryDescription(categoryDto.getCategoryDescription());
	     CategoryDto updatedCategoryDto = this.modelMapper.map(category, CategoryDto.class);
		return updatedCategoryDto;
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		this.categoryRepo.delete(category);
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return categoryDtos;
	}

}
