package com.hcl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.payload.ApiResponse;
import com.hcl.payload.CategoryDto;
import com.hcl.payload.UserDto;
import com.hcl.service.CategoryService;



@RestController
@RequestMapping("/apis/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService; 

	
	//postMapping
	@PostMapping("/addCategory")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto addCategory = categoryService.addCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(addCategory,HttpStatus.CREATED);
		
	}
	
	//putMapping
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable  Integer categoryId)
	{
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updateCategory);
	}
	
	
	//deleteMapping
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Data Deleted Succesfully", true),HttpStatus.OK);
	}
	
	//getMapping
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getUser(@PathVariable Integer categoryId)
	{
		CategoryDto category = this.categoryService.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
		
	}
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> categories = this.categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}

}
