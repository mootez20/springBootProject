package com.gestion.filmotheque.service;

import java.util.ArrayList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion.filmotheque.entities.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	    @Autowired
	    ServiceSecurity serviceSecurity;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        AppUser appUser = serviceSecurity.loadUserByUserName(username);
	        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        appUser.getAppRoles().forEach(appRole -> {
	            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(appRole.getRolename());
	            authorities.add(simpleGrantedAuthority);
	        } );
	        User user = new User(appUser.getUsername(),appUser.getPassword(),authorities);
	        return user;
	    }
	}
