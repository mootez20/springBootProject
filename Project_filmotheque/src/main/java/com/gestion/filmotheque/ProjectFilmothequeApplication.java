package com.gestion.filmotheque;

import com.gestion.filmotheque.entities.AppUser;
import com.gestion.filmotheque.service.UserDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class ProjectFilmothequeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectFilmothequeApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner commandLineRunner(UserDetailsService service){
//		return args -> {
//			service.register(AppUser.builder().username("admin").password("admin").build());
//		};
//	}

}
