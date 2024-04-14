package com.gestion.filmotheque.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Categorie {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String nom;
@OneToMany(mappedBy="categorie",cascade=CascadeType.ALL) 
	List<Film> films;
}
