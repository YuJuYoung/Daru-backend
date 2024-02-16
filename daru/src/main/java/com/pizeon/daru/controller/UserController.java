package com.pizeon.daru.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.UserDTO;
import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/create")
	public ResultDTO<?> create(@RequestBody UserDTO userDTO) {
		if (userService.isRegisteredUser(userDTO)) {
			return ResultDTO.builder()
					.success(false)
					.message("이미 존재하는 회원정보입니다.")
					.build();
		}
		
		if (userService.create(userDTO) != null) {
			return ResultDTO.builder()
					.success(true)
					.build();
		}
		return ResultDTO.builder()
				 .success(false)
				 .message("오류가 발생했습니다.")
				 .build();
	}

}
