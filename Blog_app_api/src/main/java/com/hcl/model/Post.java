package com.hcl.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter
@Getter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	private String  postTitle;
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name="category_id")
	
	@JsonIgnore
	private Category category;
	@ManyToOne
	
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> set=new HashSet<Comment>();
}
