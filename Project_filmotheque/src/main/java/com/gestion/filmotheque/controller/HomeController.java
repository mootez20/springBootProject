package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.dto.userRequest;
import com.gestion.filmotheque.entities.AppUser;
import com.gestion.filmotheque.service.IServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gestion.filmotheque.service.IServiceCategorie;
import com.gestion.filmotheque.service.IServiceFilm;

@Controller
@RequestMapping("/index/")
public class HomeController {

	@Autowired
	IServiceCategorie isc;


	@Autowired
	IServiceFilm isf;
	
	@GetMapping("all")
	public String afficheIndex(Model model){
		model.addAttribute("categories",isc.findAllCategories());
		return "Index";
	}
	
	@GetMapping("categorie/{id}")
	public String getAllFilmsByCategorieId(Model model,@PathVariable int id){
		model.addAttribute("films", isf.findFilmByCategorie(id));
		model.addAttribute("categories", isc.findAllCategories());;
		return "films";
	}


	
}
