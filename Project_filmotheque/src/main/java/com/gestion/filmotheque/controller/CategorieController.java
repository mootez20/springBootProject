package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.entities.Acteur;
import com.gestion.filmotheque.entities.Categorie;
import com.gestion.filmotheque.service.ServiceCategorie;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategorieController {
    @Autowired
    ServiceCategorie service;

    @GetMapping("all")
    public String getAllCategorie(Model model){
        model.addAttribute("categories", service.findAllCategories());
        return "AfficheCategorie";
    }

    @GetMapping("new")
    public String afficheNew(Model model){
        return "AjouterCategorie";
    }

    @PostMapping("add")
    public String add(Categorie categorie) {
        service.createCategorie(categorie);
        return "redirect:/categories/all";
    }

    @GetMapping("id")
    public Categorie getCategorie(@PathVariable  int id ){
        return service.findCategorieById(id);
    }

}
