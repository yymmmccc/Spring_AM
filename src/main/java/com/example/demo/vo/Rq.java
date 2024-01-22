package com.example.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 우리는 전역변수 Rq객체가 필요한데 이걸 스프링이 알아서 객체를 만들어서 관리해줌
public class Rq {
	
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse res) {
		
		this.req = req;
		this.res = res;
		this.session = req.getSession(); // req 많은 정보들 중에 세션을 받아오는 메서드
		
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = (Member) session.getAttribute("loginedMember");
		}
		
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
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
		this.session.setAttribute("loginedMember", member);
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
		this.session.removeAttribute("loginedMember");
	}

	public String jsReturnOnview(String msg) {
		this.req.setAttribute("msg", msg);
		return "usr/common/js";
	}

	public void init() {
		
	}
	
}
