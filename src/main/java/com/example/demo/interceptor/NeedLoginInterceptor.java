package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.util.Util;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Rq rq = (Rq) request.getAttribute("rq"); // 로그인 했는지 알 수 있는 객체
		
		if(rq.getLoginedMemberId() == 0) {
			rq.jsPrintHistoryBack("로그인 후 이용해주세요.");
			return false; // 인터셉터에서 false를 만나면 컨트롤러에 가지않는다.
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
		// 이 친구가 리턴이 되면 = true랑 같은 개념 -> 즉 컨트롤러로 넘어감
	}
	
	
}
