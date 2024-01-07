package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// HandlerInterceptor 라는 인터페이스를 상속(부모)받아 preHandle 메서드 오버라이딩
		// HttpServletRequest 는 임시저장소 (세션 등을 임시 저장)
		
		Rq rq = new Rq(request, response);
		request.setAttribute("rq", rq);
		// request 임시저장소 안에 Rq객체를 저장
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
}
