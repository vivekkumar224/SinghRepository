package com.hcl.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
