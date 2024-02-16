package com.pizeon.daru.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.User;
import com.pizeon.daru.dto.LoginDTO;
import com.pizeon.daru.repository.UserRepository;
import com.pizeon.daru.service.LoginService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final UserRepository userRepository;

	@Override
	public Optional<Long> findUserByLoginInfo(LoginDTO loginDTO) {
		List<User> foundUserList = userRepository.findByEmail(loginDTO.getEmail());
		
		if (!foundUserList.isEmpty()) {
			User foundUser = foundUserList.get(0);
			
			if (foundUser.getPassword().equals(loginDTO.getPassword())) {
				return Optional.of(foundUser.getId());
			}
		}
		return Optional.empty();
	}

}
