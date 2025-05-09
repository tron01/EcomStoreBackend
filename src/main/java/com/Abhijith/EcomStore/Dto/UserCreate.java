package com.Abhijith.EcomStore.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreate {
	 private String username;
	 private String password;
	 private String name;
	 private String address;
	 private String email;
}
