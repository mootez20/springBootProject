package com.gestion.filmotheque.service;

import java.util.List;

import com.gestion.filmotheque.entities.AppUser;

public interface IServiceSecurity {
	public AppUser loadUserByUserName(String username);
	public AppUser createAppUser(AppUser a);
	public AppUser findAppUserId(int id);
	public List<AppUser> findAllAppUser();
	public AppUser updateAppUser(AppUser a);
	public void deleteAppUser(int id);
}