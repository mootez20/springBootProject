package com.gestion.filmotheque.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gestion.filmotheque.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	    @Autowired
	    UserDetailsServiceImpl uds;

	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(uds);
	    }


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
	        http.authorizeHttpRequests((requests)-> {
	            requests.requestMatchers(HttpMethod.POST,"/api/**").permitAll();
	            requests.requestMatchers(HttpMethod.DELETE,"/api/**").permitAll();
	            requests.requestMatchers(HttpMethod.PUT,"/api/**").permitAll();
	            requests.requestMatchers("/v2/api-docs",
	                    "/swagger-resources",
	                    "/swagger-resources/**",
	                    "/configuration/ui",
	                    "/configuration/security",
	                    "/swagger-ui.html",
	                    "/webjars/**",
	                    // -- Swagger UI v3 (OpenAPI)
	                    "/v3/api-docs/**",
	                    "/swagger-ui/**","/cat/**","/film/all","/photos/**","/images/**").permitAll();
	            requests.requestMatchers("/film/new","/film/delete/**","/film/modifier/**").hasAuthority("ADMIN");
	            requests.anyRequest().authenticated();
	        }).formLogin((form) -> form
	                .permitAll().defaultSuccessUrl("/film/all",true/*???????*/)
	        ).logout((logout) -> logout.permitAll().logoutSuccessUrl("/")).exceptionHandling().accessDeniedPage("/film/forbidden");
	                http.csrf().disable();
	    return http.build();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder(){
	         BCryptPasswordEncoder bcP = new BCryptPasswordEncoder();
	         System.out.println( bcP.encode("admin"));
	        return bcP;

	    }

	}