package com.example.demo.vo;

import java.io.IOException;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;


public class Rq {
	
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse res) {
		
		this.req = req;
		this.res = res;
		
		this.session = req.getSession(); // req 많은 정보들 중에 세션을 받아오는 메서드
		
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintHistoryBack(String msg) {
		res.setContentType("text/html; charset=UTF-8;");
		
		print(Util.jsHistoryBack(msg));
	}
	
	private void print(String str) {
		try {
			res.getWriter().append(str); // res에 append를 사용해서 화면을 그릴거
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
		
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
		
	}

	public String jsReturnOnview(String msg) {
		this.req.setAttribute("msg", msg);
		return "usr/common/js";
	}
	
}
