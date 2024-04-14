package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.entities.Acteur;
import com.gestion.filmotheque.service.IServiceActeur;
import com.gestion.filmotheque.service.IServiceCategorie;
import com.gestion.filmotheque.service.IServiceFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actor/")
public class ActeurController {

		@Autowired
		IServiceFilm isf;
		@Autowired
		IServiceCategorie isc;
		@Autowired
		IServiceActeur isa;
		
		@GetMapping("all")
		public String getAllActeur(Model model){
			model.addAttribute("actors", isa.findAllActeurs());
			return "AfficheActeurs";
		}
		@GetMapping("new")
		public String afficheNew(Model model){
			return "AjouterActeur";
		}
		@GetMapping("modifier/{id}")
		public String afficheModifier(Model model,@PathVariable int id){
			model.addAttribute("acteur",isa.findActeurById(id) );
			return "UpdateActeur";
		}
		@PostMapping("add")
		public String add(Acteur a) {
			isa.createActeur(a);
			return "redirect:/actor/all";
		}
		@PostMapping("mod")
		public String mod(Acteur a) {
			isa.createActeur(a);
			return "redirect:/actor/all";
		}
		@GetMapping("delete/{id}")
		public String delete(@PathVariable int id) {
			isa.deleteActeur(id);
			return "redirect:/actor/all";
		
	}
}
