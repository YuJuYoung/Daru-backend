package com.pizeon.daru.util;

import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpSession;

@Configuration
public class HttpSessionUtil {
	
	public Long getLoginedId(HttpSession session) throws Exception {
		return (Long) session.getAttribute("loginedId");
	}
	
	public void setLoginedId(HttpSession session, Long userId) throws Exception {
		session.setAttribute("loginedId", userId);
	}
	
	public void removeLoginedId(HttpSession session) throws Exception {
		session.removeAttribute("loginedId");
	}
	
	public boolean isLoginedId(HttpSession session, Long userId) throws Exception {
		Long loginedId = (Long) session.getAttribute("loginedId");
		
		if (loginedId != null) {
			return loginedId.equals(userId);
		}
		return false;
	}
	
}
