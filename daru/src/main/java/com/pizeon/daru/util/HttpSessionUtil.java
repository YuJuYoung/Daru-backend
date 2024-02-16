package com.pizeon.daru.util;

import jakarta.servlet.http.HttpSession;

public class HttpSessionUtil {
	
	public static Long getLoginedId(HttpSession session) {
		return (Long) session.getAttribute("loginedId");
	}
	
	public static void setLoginedId(HttpSession session, Long userId) {
		session.setAttribute("loginedId", userId);
	}
	
	public static void removeLoginedId(HttpSession session) {
		session.removeAttribute("loginedId");
	}
	
	public static boolean isLoginedId(HttpSession session, Long userId) {
		Long loginedId = (Long) session.getAttribute("loginedId");
		
		if (loginedId != null) {
			return loginedId.equals(userId);
		}
		return false;
	}
	
}
