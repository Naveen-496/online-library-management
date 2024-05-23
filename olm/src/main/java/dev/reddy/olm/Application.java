package dev.reddy.olm;

import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.entity.Role;
import dev.reddy.olm.entity.User;
import dev.reddy.olm.enums.Authority;
import dev.reddy.olm.repository.CredentialRepository;
import dev.reddy.olm.repository.RoleRepository;
import dev.reddy.olm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, CredentialRepository credentialRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if ( userRepository.count() != 0 ) {
				return;
//				credentialRepository.deleteAll();
//				userRepository.deleteAll();
//				roleRepository.deleteAll();
			}

			var role = new Role();
			role.setName("Admin");
			role.setAuthority(Authority.SUPER_ADMIN);

//
			var userRole = new Role();
			userRole.setName("User");
			userRole.setAuthority(Authority.USER);
//
			var credentials = new Credentials();
			credentials.setPassword(passwordEncoder.encode( "System@496"));


			var userCredentials = new Credentials();
			userCredentials.setPassword(passwordEncoder.encode("Naveen@496"));

			var adminUser = User.builder().userId(UUID.randomUUID().toString())
					.email("system@gmail.com")
					.bio("This is a system and not a user")
					.phone("System")
					.firstName("System")
					.lastName("System")
					.enabled(true)
					.role(role)
					.build();

			var user = User.builder().userId(UUID.randomUUID().toString())
					.email("naveen@gmail.com")
					.bio("Developer, Swimmer, Sleeper")
					.phone("7779898008")
					.firstName("Naveen")
					.lastName("Reddy")
					.enabled(true)
					.role(userRole)
					.build();

			credentials.setUser(adminUser);
            userCredentials.setUser(user);
			userRepository.save(user);
			userRepository.save(adminUser);
			roleRepository.save(role);
			roleRepository.save(userRole);
			credentialRepository.save(credentials);
            credentialRepository.save(userCredentials);
		};
	}

}
