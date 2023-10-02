package com.hcl.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer categoryId;
	private String categoryName;
	private String categoryDescription;
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
	
	private List<Post> posts=new ArrayList<Post>();

}
