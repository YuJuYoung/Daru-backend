package com.pizeon.daru.dto.user;

import com.pizeon.daru.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateDTO {
	
	@Email
	@NotEmpty
	private String email;
	
	@Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).*$/")
	@Size(min = 8, max = 20)
	private String password;
	
	@Pattern(regexp = "/^\\d+$/")
	@Size(min = 10, max = 11)
	private String phoneNumber;
	
	@Pattern(regexp = "/^\\S+$/")
	private String name;
	
	public static UserCreateDTO fromEntity(User user) {
		return new UserCreateDTO(user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getName());
	}

}
