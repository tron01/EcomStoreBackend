package com.Abhijith.EcomStore.Controller;

import com.Abhijith.EcomStore.Dto.TokenDto;
import com.Abhijith.EcomStore.Dto.UserRequest;
import com.Abhijith.EcomStore.Model.Users;
import com.Abhijith.EcomStore.Repository.UserRepository;
import com.Abhijith.EcomStore.Service.CustomUserDetailService;
import com.Abhijith.EcomStore.Service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AthuController {

	private final JwtService jwtService;
	private final AuthenticationManager manager;
	private  final UserRepository userRepository;
	
	
	@GetMapping("/login")
	private ResponseEntity<TokenDto> login(@RequestBody UserRequest request) {
		manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserDetails user= userRepository.findByUsername(request.getUsername()).orElseThrow(
				()->new RuntimeException("User not found"));
		
		String token = jwtService.generateToken(user);
		
		return ResponseEntity.ok(new TokenDto(token));
	}
	
}
