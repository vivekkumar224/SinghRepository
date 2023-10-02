package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.payload.ApiResponse;
import com.hcl.payload.UserDto;
import com.hcl.service.USerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/apis/users")
public class UserController {
	
	@Autowired
	private USerService uSerService;
	
	
	
	//post-users
	@PostMapping("/addUSer")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto savedUser = uSerService.addUSer(userDto);
		
		return new ResponseEntity<UserDto>(savedUser,HttpStatus.CREATED);
	}
	
	//put-users
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
	{
		UserDto updatedUSer = uSerService.updateUSer(userDto, userId);
		
		return ResponseEntity.ok(updatedUSer);
	}
	
	
	//delete-users
	@PreAuthorize("hasRole('ADMN')")
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deletUser(@PathVariable Integer userId)
	{
		 uSerService.DeleteUSer(userId);
		
		return  new ResponseEntity<ApiResponse>(new ApiResponse("Data Deleted Successfully",true),HttpStatus.OK);
	}
	
	//get-users
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> allUsers = uSerService.getAllUsers();
		
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId)
	{
		UserDto userDto = uSerService.getUSer(userId);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
    
	
}
