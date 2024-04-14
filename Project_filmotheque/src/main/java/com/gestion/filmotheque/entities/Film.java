package com.gestion.filmotheque.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Film {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(unique = true)
private String titre;
private String description;
private int anneeparution;
private String photo;
@ManyToOne
private Categorie categorie;
@ManyToMany
private List<Acteur> acteurs;

}
