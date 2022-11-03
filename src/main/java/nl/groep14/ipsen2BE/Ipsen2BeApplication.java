package nl.groep14.ipsen2BE;

import nl.groep14.ipsen2BE.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
