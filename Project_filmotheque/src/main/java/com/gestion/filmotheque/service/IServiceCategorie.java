package com.gestion.filmotheque.service;

import java.util.List;

import com.gestion.filmotheque.entities.Categorie;

public interface IServiceCategorie {
	public Categorie createCategorie(Categorie f);
	public Categorie findCategorieById(int id);
	public List<Categorie> findAllCategories();
	public Categorie updateCategorie(Categorie f);
	public void deleteCategorie(int id);
}
