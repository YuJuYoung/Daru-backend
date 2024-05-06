package com.pizeon.daru.service;

import com.pizeon.daru.dto.user.UserCreateDTO;

public interface UserService {
	
	public UserCreateDTO create(UserCreateDTO userCreateDTO) throws Exception;
	public boolean isRegisteredUser(UserCreateDTO userCreateDTO) throws Exception;
	
}
