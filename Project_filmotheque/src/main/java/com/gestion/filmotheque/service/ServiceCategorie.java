package com.gestion.filmotheque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.filmotheque.entities.Categorie;
import com.gestion.filmotheque.repository.CategoryRepository;

@Service
public class ServiceCategorie implements IServiceCategorie {

	@Autowired
	CategoryRepository CategoryRepository;
	
	@Override
	public Categorie createCategorie(Categorie f) {
		return CategoryRepository.save(f);
	}
	
	@Override
	public Categorie findCategorieById(int id) {
		return CategoryRepository.findById(id).get();
	}
	
	@Override
	public List<Categorie> findAllCategories(){
		return CategoryRepository.findAll();
	}
	
	@Override
	public Categorie updateCategorie(Categorie f) {
		return CategoryRepository.save(f);
	}
	
	@Override
	public void deleteCategorie(int id) {
		CategoryRepository.deleteById(id);
	}
}
