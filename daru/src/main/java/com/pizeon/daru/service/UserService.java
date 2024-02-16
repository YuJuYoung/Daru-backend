package com.pizeon.daru.service;

import com.pizeon.daru.dto.UserDTO;

public interface UserService {
	
	public UserDTO create(UserDTO userDTO);
	public boolean isRegisteredUser(UserDTO userDTO);
	
}
