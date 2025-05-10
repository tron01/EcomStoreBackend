package com.Abhijith.EcomStore.Service;

import com.Abhijith.EcomStore.Model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

	private final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour
	private final String secretKey = "secre1qddwddqdqd41414f14fwffwffwfffwff1241444144qrtKeyfafqqtqrqwfwfmwfqfq2";
	private final SecretKey signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
	
	public String generateToken(Users user) {
		return Jwts.builder()
				 .subject(String.valueOf(user.getId()))
				 .issuedAt(new Date())
				 .claim("role",user.getRole().name()) //setting role in token
				 .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
				 .signWith(signingKey)
				 .compact();
	}
	
	private Claims getClaimsFromToken(String token) {
		return Jwts.parser()
					   .verifyWith(signingKey)
					   .build()
					   .parseSignedClaims(token)
					   .getPayload();
	}
	public String extractUserId(String token) {
		return getClaimsFromToken(token).getSubject();
	}

	public boolean validateToken(String token, UserDetails user) {
		String userIdFromToken = extractUserId(token);
		if(user instanceof Users){
			
			long actualUserId= ((Users) user).getId();
			
			return userIdFromToken.equals(String.valueOf(actualUserId))
						   && !isTokenExpired(token);
		}
		return false;
	}
	
	private boolean isTokenExpired(String token) {
		return getClaimsFromToken(token).getExpiration().before(new Date());
	}
	
}
