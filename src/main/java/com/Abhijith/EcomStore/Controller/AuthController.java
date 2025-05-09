package com.Abhijith.EcomStore.Controller;

import com.Abhijith.EcomStore.Dto.TokenDto;
import com.Abhijith.EcomStore.Dto.UserCreate;
import com.Abhijith.EcomStore.Dto.UserCreateResponse;
import com.Abhijith.EcomStore.Dto.UserLogin;
import com.Abhijith.EcomStore.Model.Users;
import com.Abhijith.EcomStore.Repository.UserRepository;
import com.Abhijith.EcomStore.Service.JwtService;
import com.Abhijith.EcomStore.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	private final JwtService jwtService;
	private final AuthenticationManager manager;
	private  final UserRepository userRepository;
	private final UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody UserLogin request) {
		
		manager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		Users user= userRepository.findByUsername(request.getUsername()).orElseThrow(
				()->new RuntimeException("User not found"));
		
		String token = jwtService.generateToken(user);
		
		return ResponseEntity.ok(new TokenDto(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserCreateResponse> register (@RequestBody UserCreate request){
		 return ResponseEntity.ok(userService.register(request));
	}
	
}
