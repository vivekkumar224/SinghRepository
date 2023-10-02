package com.hcl.reporsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	
	Optional<User> findByEmail(String email);

}
