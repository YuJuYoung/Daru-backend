package com.pizeon.daru.domain;

import com.pizeon.daru.dto.user.UserCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String name;
	
	public static User fromDTO(UserCreateDTO userCreateDTO) {
		return builder()
				.email(userCreateDTO.getEmail())
				.password(userCreateDTO.getPassword())
				.phoneNumber(userCreateDTO.getPhoneNumber())
				.name(userCreateDTO.getName())
				.build();
	}
	
}
