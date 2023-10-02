package com.hcl.service;

import java.util.List;

import com.hcl.payload.UserDto;

public interface USerService {
	
	
	
	public UserDto addUSer(UserDto userDto);
	public UserDto updateUSer( UserDto userDto,int id);
	public UserDto getUSer(int id);
	public List<UserDto> getAllUsers();
	public void DeleteUSer(int id);

}
