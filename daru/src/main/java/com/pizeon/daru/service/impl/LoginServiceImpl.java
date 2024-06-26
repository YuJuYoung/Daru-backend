package com.pizeon.daru.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<Long> findUserByLoginInfo(LoginDTO loginDTO) throws Exception {
		List<User> foundUserList = userRepository.findByEmail(loginDTO.getEmail());
		
		if (!foundUserList.isEmpty()) {
			User foundUser = foundUserList.get(0);
			
			if (passwordEncoder.matches(loginDTO.getPassword(), foundUser.getPassword())) {
				return Optional.of(foundUser.getId());
			}
		}
		return Optional.empty();
	}

}
