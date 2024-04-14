package com.gestion.filmotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.filmotheque.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
	AppUser findByUsername(String username);
	}