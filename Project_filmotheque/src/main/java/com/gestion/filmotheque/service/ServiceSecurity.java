package com.gestion.filmotheque.service;

import java.util.List;

import com.gestion.filmotheque.entities.AppRole;
import com.gestion.filmotheque.repository.AppRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.filmotheque.entities.AppUser;
import com.gestion.filmotheque.repository.AppUserRepository;

import javax.management.relation.Role;

@Service
public class ServiceSecurity implements IServiceSecurity {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public AppUser loadUserByUserName(String username) {
        AppUser user = appUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    @Override
    public AppUser createAppUser(AppUser a) {
        AppUser newUser = new AppUser();
        newUser.setUsername("admin");
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        newUser.setPassword(bcp.encode("admin"));
        return appUserRepository.save(a);
    }

    @PostConstruct
    public void createAppDefault() {
        // Check if the ADMIN role exists, if not, create it
        AppRole role = appRoleRepository.findByRolename("ADMIN");
        if (role == null) {
            role = new AppRole("ADMIN");
            appRoleRepository.save(role);
        }

        // Check if the admin user exists
        AppUser newUser = appUserRepository.findByUsername("admin");
        if (newUser == null) {
            // If the user doesn't exist, create a new one
            newUser = new AppUser();
            newUser.setUsername("admin");
            BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
            newUser.setPassword(bcp.encode("admin"));
        }

        // Add the ADMIN role to the user
        newUser.addRoles(role);

        // Save the user
        appUserRepository.save(newUser);
    }

    @Override
    public AppUser findAppUserId(int id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public List<AppUser> findAllAppUser() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser updateAppUser(AppUser a) {
        return appUserRepository.save(a);
    }

    @Override
    public void deleteAppUser(int id) {
        appUserRepository.deleteById(id);

    }

}


