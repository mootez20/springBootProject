package com.gestion.filmotheque.service;

import java.util.List;

import com.gestion.filmotheque.entities.*;

public interface IServiceActeur {
	public Acteur createActeur(Acteur a)  ;
	public Acteur findActeurById(int id) ;
	public List<Acteur> findAllActeurs();
	public Acteur updateActeur(Acteur a) ;
	public void deleteActeur (int id);
}
