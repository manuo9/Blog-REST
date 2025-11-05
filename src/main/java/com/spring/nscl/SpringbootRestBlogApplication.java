package com.spring.nscl;

import com.spring.nscl.entity.Role;
import com.spring.nscl.repository.RoleRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST APIs",
				description = "Spring Boot Blog App REST APIs Documentation" ,
				version = "v1.0",
				contact = @Contact(
						name = "Manu Prasad",
						email = "manuprasad689@gmail.com",
						url = "gitlink"
						),
				license  = @License(
				name = "Apache 2.0")
				)
)
public class SpringbootRestBlogApplication implements CommandLineRunner {

   // can write @beans here
    @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestBlogApplication.class, args);
	}

	@Autowired
   private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role();
		admin.setName("Role_ADMIN");
		roleRepository.save(admin);

		Role user = new Role();
		user.setName("Role_USER");
		roleRepository.save(user);
	}
}
