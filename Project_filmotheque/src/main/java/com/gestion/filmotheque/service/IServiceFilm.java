package com.gestion.filmotheque.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gestion.filmotheque.entities.Film;

public interface IServiceFilm {

	public Film createFilm(Film f);
	public Film findFilmById(int id);
	public List<Film> findAllFilms();
	public Film updateFilm(Film f);
	public void deleteFilm(int id);
	public List<Film> findFilmByAnnee(int annee);
	public List<Film> findFilmByCategorie(int id);
	public Page<Film> findPaginatedFilms(int pageNum,int pageSize,String sortField,String sortDir);


 
}
