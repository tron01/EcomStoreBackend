package com.Abhijith.EcomStore.Service;

import com.Abhijith.EcomStore.Dto.UserCreate;
import com.Abhijith.EcomStore.Dto.UserCreateResponse;
import com.Abhijith.EcomStore.Dto.UserDTO;
import com.Abhijith.EcomStore.Model.Role;
import com.Abhijith.EcomStore.Model.UserInfo;
import com.Abhijith.EcomStore.Model.Users;
import com.Abhijith.EcomStore.Repository.UserInfoRepository;
import com.Abhijith.EcomStore.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final UserInfoRepository userInfoRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserCreateResponse register(UserCreate request){
		
		Users user = new Users();
		user.setUsername(request.getUsername());
		user.setRole(Role.USERS);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setIsActive(true);
		user.setIsNonLocked(true);
		
		Users savedUser = userRepository.save(user);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUser(savedUser);
		userInfo.setName(request.getName());
		userInfo.setEmail(request.getEmail());
		userInfo.setAddress(request.getAddress());
		userInfo.setCreatedAt(LocalDateTime.now());
		userInfo.setUpdatedAt(LocalDateTime.now());
		
		userInfoRepository.save(userInfo);
		UserDTO userDTO = UserDTO.builder()
								  .id(savedUser.getId())
								  .username(savedUser.getUsername())
								  .name(userInfo.getName())
								  .email(userInfo.getEmail())
								  .createdAt(userInfo.getCreatedAt())
								  .build();
								  
		return UserCreateResponse.builder()
					   .status("success")
					   .message("User successfully registered")
					   .data(userDTO)
					   .build();
	}
	
}
