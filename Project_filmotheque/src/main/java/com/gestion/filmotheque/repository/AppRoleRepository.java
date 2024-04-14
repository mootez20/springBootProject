package com.gestion.filmotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.filmotheque.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {
	AppRole findByRolename(String rolename);

}
