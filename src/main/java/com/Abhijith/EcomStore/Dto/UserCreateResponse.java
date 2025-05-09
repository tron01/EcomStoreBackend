package com.Abhijith.EcomStore.Dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreateResponse {
	private String status; // success or error
	private String message; // Detailed message like "User successfully registered"
	private UserDTO data;
}
