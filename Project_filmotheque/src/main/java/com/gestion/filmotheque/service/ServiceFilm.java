package com.gestion.filmotheque.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.repository.FilmRepository;

@Service
public class ServiceFilm implements IServiceFilm {
	@Autowired
	FilmRepository filmRepository;
	
	@Override
	public Film createFilm(Film f) {
		return filmRepository.save(f);
	}
	
	@Override
	public Film findFilmById(int id) {
		return filmRepository.findById(id).get();
	}
	
	@Override
	public List<Film> findAllFilms(){
		return filmRepository.findAll();
	}
	
	@Override
	public Film updateFilm(Film f) {
		return filmRepository.save(f);
	}
	
	@Override
	public void deleteFilm(int id) {
		filmRepository.deleteById(id);
	}
	
	@Override
	public List<Film> findFilmByAnnee(int annee) {
		return filmRepository.findByAnneeparution(annee);
	}
	
	@Override
	public List<Film> findFilmByCategorie(int id){
		return filmRepository.findByCategorieId(id);

	}
	
	@Override
	public Page<Film> findPaginatedFilms(int pageNum, int pageSize,String sortField,String sortDir) {
		Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
		return filmRepository.findAll(pageable);
	}
	
}
