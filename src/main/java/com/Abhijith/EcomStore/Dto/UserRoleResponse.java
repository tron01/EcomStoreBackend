package com.Abhijith.EcomStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserRoleResponse {
public String username;
public List<String> roles;
}
