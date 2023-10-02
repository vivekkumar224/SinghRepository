package com.hcl.reporsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.model.Category;
import com.hcl.model.Post;
import com.hcl.model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String keybord);

}
