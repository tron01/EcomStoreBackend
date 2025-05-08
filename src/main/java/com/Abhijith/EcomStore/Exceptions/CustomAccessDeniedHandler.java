package com.Abhijith.EcomStore.Exceptions;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(accessDeniedException.getMessage()));
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
	}

}
