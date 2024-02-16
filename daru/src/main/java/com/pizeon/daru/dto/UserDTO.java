package com.pizeon.daru.dto;

import com.pizeon.daru.domain.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {
	
	private Long id;
	private String email;
	private String password;
	private String phoneNumber;
	private String name;
	
	public static UserDTO fromEntity(User user) {
		return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getName());
	}

}
