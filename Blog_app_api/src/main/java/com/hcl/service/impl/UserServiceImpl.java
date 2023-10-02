package com.hcl.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.exception.ResourceNotFoundException;
import com.hcl.model.User;
import com.hcl.payload.UserDto;
import com.hcl.reporsitory.UserRepo;
import com.hcl.service.USerService;
@Service
public class UserServiceImpl implements USerService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUSer(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUSer(userDto);
		User savedUser = userRepo.save(user);
		UserDto savedUserDto=this.userToDto(savedUser);
		return savedUserDto;
	}

	@Override
	public UserDto updateUSer(UserDto userDto, int id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		User savedUser = userRepo.save(user);
		UserDto saveduserToDto = this.userToDto(savedUser);
		return saveduserToDto;
	}

	@Override
	public UserDto getUSer(int id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		UserDto userToDto = this.userToDto(user);
		return userToDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void DeleteUSer(int id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		this.userRepo.delete(user);
		
	}
	
	public User dtoToUSer(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto,User.class);
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return user;
	}
	public UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		/*
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setEmail(user.getEmail()); userDto.setAbout(user.getAbout());
		 * userDto.setPassword(user.getPassword());
		 */
		return userDto;
	}

}
