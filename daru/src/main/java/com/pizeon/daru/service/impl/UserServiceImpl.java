package com.pizeon.daru.service.impl;

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

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = userRepository.save(User.fromDTO(userDTO));
		return UserDTO.fromEntity(user);
	}

	@Override
	public boolean isRegisteredUser(UserDTO userDTO) {
		return !userRepository.findByEmail(userDTO.getEmail()).isEmpty();
	}

}
