package com.Abhijith.EcomStore.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
	
	
	public String generateToken(UserDetails user) {
	
	}

	public String extractUsername(String token) {
	}

	public boolean validateToken(String token, UserDetails user) {
	}
}
