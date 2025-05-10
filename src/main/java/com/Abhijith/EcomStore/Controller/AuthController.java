package com.Abhijith.EcomStore.Controller;

import com.Abhijith.EcomStore.Config.CookieProperties;
import com.Abhijith.EcomStore.Dto.UserCreate;
import com.Abhijith.EcomStore.Dto.UserCreateResponse;
import com.Abhijith.EcomStore.Dto.UserLogin;
import com.Abhijith.EcomStore.Model.Users;
import com.Abhijith.EcomStore.Repository.UserRepository;
import com.Abhijith.EcomStore.Service.JwtService;
import com.Abhijith.EcomStore.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
	private final UserRepository userRepository;
	private final UserService userService;
	private final CookieProperties cookieProperties;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLogin request, HttpServletResponse response) {
		
		manager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		Users user= userRepository.findByUsername(request.getUsername()).orElseThrow(
				()->new RuntimeException("User not found"));
		
		String token = jwtService.generateToken(user);
		
		Cookie cookie = new Cookie("jwt", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60);
		response.addCookie(cookie);
		
		return ResponseEntity.ok("Login success");
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserCreateResponse> register (@RequestBody UserCreate request){
		 return ResponseEntity.ok(userService.register(request));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
		cookie.setHttpOnly(true);
		cookie.setSecure(cookieProperties.isSecure());
		cookie.setPath("/");
		cookie.setMaxAge(0); // delete
		
		response.addCookie(cookie);
		
		return ResponseEntity.ok("Logged out");
	}
	
	
}
