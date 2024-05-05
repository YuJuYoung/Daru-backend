package com.pizeon.daru.service;

import com.pizeon.daru.dto.UserDTO;

public interface UserService {
	
	public UserDTO create(UserDTO userDTO) throws Exception;
	public boolean isRegisteredUser(UserDTO userDTO) throws Exception;
	
}
