package com.pizeon.daru.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.User;
import com.pizeon.daru.dto.user.UserCreateDTO;
import com.pizeon.daru.repository.UserRepository;
import com.pizeon.daru.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserCreateDTO create(UserCreateDTO userCreateDTO) throws Exception {
		String encodedPassword = passwordEncoder.encode(userCreateDTO.getPassword());
		userCreateDTO.setPassword(encodedPassword);
		User user = userRepository.save(User.fromDTO(userCreateDTO));
		return UserCreateDTO.fromEntity(user);
	}

	@Override
	public boolean isRegisteredUser(UserCreateDTO userCreateDTO) throws Exception {
		return !userRepository.findByEmail(userCreateDTO.getEmail()).isEmpty();
	}

}
