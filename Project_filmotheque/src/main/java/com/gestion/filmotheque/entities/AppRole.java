package com.gestion.filmotheque.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique=true)
	private String rolename;
	private String description;
	@ManyToMany(mappedBy ="appRoles")
	@Getter
	@Setter
	private List<AppUser> appUsers ;

	public AppRole(String roleName) {
		this.rolename = roleName;
	}

	
}