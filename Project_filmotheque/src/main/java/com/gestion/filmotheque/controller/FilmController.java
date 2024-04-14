package com.gestion.filmotheque.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.service.IServiceActeur;
import com.gestion.filmotheque.service.IServiceCategorie;
import com.gestion.filmotheque.service.IServiceFilm;

@Controller
@RequestMapping("film")
public class FilmController {
	
	@Autowired
	IServiceFilm isf;
	@Autowired
	IServiceCategorie isc;
	@Autowired
	IServiceActeur isa;
	// private String uploadDirectory=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\photos";
	private  String uploadDirectory = "src/main/resources/static/photos";
	
	@GetMapping("all")
	public String listeFilms (Model model) {
		model.addAttribute("categories",isf.findAllFilms());
		return getPage(1, model, "titre","asc");
	}
	

	@GetMapping("page/{pageNum}")
	public String getPage(@PathVariable int pageNum,Model model,@RequestParam String sortField,@RequestParam String sortDir) {
		int pageSize = 3;
		Page<Film> page = isf.findPaginatedFilms(pageNum,pageSize,sortField,sortDir);
		model.addAttribute("films",page.getContent());
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("currentPage",pageNum);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("sortField",sortField);
		model.addAttribute("reverseDir",sortDir.equals("asc")?"desc":"asc");
		return "Affiche";
	}
	@PostMapping("annee")
	public String getAllFilmsByAnnee(Model model,int annee){
		model.addAttribute("films", isf.findFilmByAnnee(annee));
		return "Affiche";
	}
	
	@PostMapping("categorie")
	public String getAllFilmsByCategorieId(Model model,int id){
		model.addAttribute("films", isf.findFilmByCategorie(id));
		return "Affiche";
	}
	@GetMapping("new")
	public String afficheNewForm(Model model) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		model.addAttribute("categories",isc.findAllCategories());
		model.addAttribute("acteurs",isa.findAllActeurs());
		model.addAttribute("CourrantDate",year);
		return "Ajout";
	}
	
	@PostMapping("add")
    public String add(Model model, Film f, @RequestParam("file") MultipartFile multipartFile) {
        
		String fileName=multipartFile.getOriginalFilename();
		System.out.println(fileName);
		Path fileNameAndPath= Paths.get(uploadDirectory).resolve(fileName);
		
		try { 
			
            try {
				System.out.println("path : " + fileNameAndPath);
    			Files.copy(multipartFile.getInputStream(),fileNameAndPath);
    		}
    		catch(IOException e){
    			e.printStackTrace();
    		}
            f.setPhoto(fileName);
            isf.createFilm(f);
			return "redirect:/film/all";
        }catch (DataIntegrityViolationException e) {
            model.addAttribute("msgerreur","ce titre de film est déja existe ");
            model.addAttribute("film",f);
            return "Ajout";
        }
    }

	
	/*@GetMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		isf.deleteFilm(id);
		return "redirect:/film/all";
	}*/
	@DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {
		isf.deleteFilm(id);
		return "redirect:/film/all";
    }
	@GetMapping("modifier/{id}")
	public String afficheModifier(Model model,@PathVariable int id){
		model.addAttribute("categories",isc.findAllCategories() );
		model.addAttribute("film",isf.findFilmById(id) );
		model.addAttribute("acteurs",isa.findAllActeurs());
		return "Update";
	}
	
	@GetMapping("details/{id}")
	public String detailsFilm (Model model,@PathVariable int id) {
		model.addAttribute("categories",isc.findAllCategories() );
		model.addAttribute("film",isf.findFilmById(id) );
		model.addAttribute("acteurs",isa.findAllActeurs());
		return "Details";
	}
	
	@PostMapping("mod")
	public String mod(Film f,@RequestParam("file")MultipartFile mpf) {

		if (!mpf.isEmpty()) {
			String fileName = mpf.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
			try {
				Files.write(fileNameAndPath, mpf.getBytes());

			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			f.setPhoto(fileName);
		} 
		else
		{
			f.setPhoto(isf.findFilmById(f.getId()).getPhoto());
		}
			isf.updateFilm(f);
		return "redirect:/film/all";
	}
	
}
