package com.Abhijith.EcomStore.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDTO {
	private Long id;
	private String username;
	private String name;
	private String email;
	private LocalDateTime createdAt;
}
