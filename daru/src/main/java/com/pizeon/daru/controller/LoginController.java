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
	private final HttpSessionUtil httpSessionUtil;
	
	@PostMapping("")
	public ResultDTO<Long> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
		try {
			Optional<Long> foundUserId = loginService.findUserByLoginInfo(loginDTO);
			
			if (!foundUserId.isEmpty()) {
				httpSessionUtil.setLoginedId(request.getSession(), foundUserId.get());
				
				return ResultDTO.<Long>builder()
						.success(true)
						.data(foundUserId.get())
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.<Long>builder()
				.success(false)
				.message("로그인 정보가 일치하지 않습니다.")
				.build();
	}
	
	@PostMapping("/logout")
	public ResultDTO<?> logout(HttpServletRequest request, @RequestBody String loginedId) {
		try {
			HttpSession session = request.getSession();
			
			if (httpSessionUtil.isLoginedId(session, Long.parseLong(loginedId))) {
				httpSessionUtil.removeLoginedId(session);
				
				return ResultDTO.builder()
						.success(true)
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultDTO.builder()
				.success(false)
				.message("로그아웃에 실패했습니다.")
				.build();
	}

}
