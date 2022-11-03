package nl.groep14.ipsen2BE;

import nl.groep14.ipsen2BE.Services.UserService;
import nl.groep14.ipsen2BE.config.RsaKeyProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class Ipsen2BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ipsen2BeApplication.class, args);
	}


//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.addRolAanGebruiker("admin", "ROLE_ADMIN");
//			userService.addRolAanGebruiker("jandea", "ROLE_ADMIN");
//			System.out.println(userService.getGebruiker("jandeman").getRoles());
//		};
//	}
}
