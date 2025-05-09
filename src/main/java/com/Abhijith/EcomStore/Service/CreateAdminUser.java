package com.Abhijith.EcomStore.Service;

import com.Abhijith.EcomStore.Model.Role;
import com.Abhijith.EcomStore.Model.Users;
import com.Abhijith.EcomStore.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAdminUser {
	
		@Bean
		public CommandLineRunner  AdminCreate(UserRepository userRepository,PasswordEncoder passwordEncoder){
			return args->{
				
				if(userRepository.findByUsername("admin").isEmpty()){
					Users admin = new Users();
					admin.setUsername("admin");
					admin.setRole(Role.ADMIN);
					admin.setPassword(passwordEncoder.encode("admin"));
					admin.setIsActive(true);
					admin.setIsNonLocked(true);
					userRepository.save(admin);
					System.out.println("Admin Created");
				}else {
					System.out.println("Admin exist");
				}
			};
		}
}
