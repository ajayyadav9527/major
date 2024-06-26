package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.CatergoryRepository;

@Service
public class CategoryService {
	@Autowired
	CatergoryRepository catergoryRepository;
	public List<Category> getAllCategory(){
		return catergoryRepository.findAll();
	}
	public void addCategory(Category category) {
		catergoryRepository.save(category);
	}
	
	public void removeCategoryById(int id) {
		catergoryRepository.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(int id) {
		return catergoryRepository.findById(id);
	}
	
	
}
