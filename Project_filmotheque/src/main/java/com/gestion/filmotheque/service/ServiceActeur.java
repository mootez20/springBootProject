package com.gestion.filmotheque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.filmotheque.entities.*;
import com.gestion.filmotheque.repository.*;
import com.gestion.filmotheque.service.*;

@Service
public class ServiceActeur implements IServiceActeur{

	@Autowired
	ActeurRepository ar;
	
	@Override
	public Acteur createActeur(Acteur a) {
		// TODO Auto-generated method stub
		return ar.save(a);
	}

	@Override
	public Acteur findActeurById(int id) {
		// TODO Auto-generated method stub
		return ar.findById(id).get();
	}

	@Override
	public List<Acteur> findAllActeurs() {
		// TODO Auto-generated method stub
		return ar.findAll();
	}

	@Override
	public Acteur updateActeur(Acteur a) {
		// TODO Auto-generated method stub
		return ar.save(a);
	}

	@Override
	public void deleteActeur(int id) {
		// TODO Auto-generated method stub
		ar.deleteById(id);
	}

}
