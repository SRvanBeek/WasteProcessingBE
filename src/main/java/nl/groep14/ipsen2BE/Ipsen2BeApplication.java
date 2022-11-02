package nl.groep14.ipsen2BE;

import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import nl.groep14.ipsen2BE.config.RsaKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import nl.groep14.ipsen2BE.Services.UserService;

import java.util.ArrayList;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class Ipsen2BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ipsen2BeApplication.class, args);
	}


//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.addRolAanGebruiker("Piet", "ROLE_USER");
//		};
//	}
}
