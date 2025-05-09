package com.Abhijith.EcomStore.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@GetMapping("/")
	private ResponseEntity<?> Dashboard(){
		return ResponseEntity.ok("welcome to User Dashboard");
	}
	
}
