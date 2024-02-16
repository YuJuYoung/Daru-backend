package com.pizeon.daru.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizeon.daru.dto.LoginDTO;
import com.pizeon.daru.dto.cmmn.ResultDTO;
import com.pizeon.daru.service.LoginService;
import com.pizeon.daru.util.HttpSessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
	
	private final LoginService loginService;
	
	@PostMapping("")
	public ResultDTO<Long> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
		Optional<Long> foundUserId = loginService.findUserByLoginInfo(loginDTO);
		
		if (!foundUserId.isEmpty()) {
			HttpSessionUtil.setLoginedId(request.getSession(), foundUserId.get());
			
			return ResultDTO.<Long>builder()
					.success(true)
					.data(foundUserId.get())
					.build();
		}
		return ResultDTO.<Long>builder()
				.success(false)
				.message("로그인 정보가 일치하지 않습니다.")
				.build();
	}
	
	@PostMapping("/logout")
	public ResultDTO<?> logout(HttpServletRequest request, @RequestBody String loginedId) {
		HttpSession session = request.getSession();
		
		if (HttpSessionUtil.isLoginedId(session, Long.parseLong(loginedId))) {
			HttpSessionUtil.removeLoginedId(session);
			
			return ResultDTO.builder()
					.success(true)
					.build();
		}
		
		return ResultDTO.builder()
				.success(false)
				.message("로그아웃에 실패했습니다.")
				.build();
	}

}
