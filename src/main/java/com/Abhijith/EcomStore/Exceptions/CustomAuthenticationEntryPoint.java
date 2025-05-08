package com.Abhijith.EcomStore.Exceptions;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(authException.getMessage()));
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
	}

}
