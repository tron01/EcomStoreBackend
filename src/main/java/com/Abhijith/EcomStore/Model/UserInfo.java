package com.Abhijith.EcomStore.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private String address;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private Users user;
}
