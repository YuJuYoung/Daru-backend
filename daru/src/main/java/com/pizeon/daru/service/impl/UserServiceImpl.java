package com.pizeon.daru.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pizeon.daru.domain.User;
import com.pizeon.daru.dto.UserDTO;
import com.pizeon.daru.repository.UserRepository;
import com.pizeon.daru.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDTO create(UserDTO userDTO) throws Exception {
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(encodedPassword);
		User user = userRepository.save(User.fromDTO(userDTO));
		return UserDTO.fromEntity(user);
	}

	@Override
	public boolean isRegisteredUser(UserDTO userDTO) throws Exception {
		return !userRepository.findByEmail(userDTO.getEmail()).isEmpty();
	}

}
