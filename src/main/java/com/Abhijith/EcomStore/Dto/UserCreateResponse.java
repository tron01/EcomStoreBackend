package com.Abhijith.EcomStore.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserCreateResponse {
	private String status; // success or error
	private String message; // Detailed message like "User successfully registered"
	public List<String> roles;
}
