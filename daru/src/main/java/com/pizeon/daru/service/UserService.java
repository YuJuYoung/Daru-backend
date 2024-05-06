package com.pizeon.daru.service;

import java.util.Optional;

import com.pizeon.daru.dto.user.UserCreateDTO;

public interface UserService {
	
	public Optional<UserCreateDTO> create(UserCreateDTO userCreateDTO) throws Exception;
	public boolean isRegisteredUser(UserCreateDTO userCreateDTO) throws Exception;
	
}
