package com.hcl.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
