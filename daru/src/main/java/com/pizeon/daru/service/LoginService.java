package com.pizeon.daru.service;

import java.util.Optional;

import com.pizeon.daru.dto.LoginDTO;

public interface LoginService {
	
	public Optional<Long> findUserByLoginInfo(LoginDTO loginDTO) throws Exception;

}
