package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{

	private Rq rq;
	
	@Autowired
	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// HandlerInterceptor 라는 인터페이스를 상속(부모)받아 preHandle 메서드 오버라이딩
		// HttpServletRequest 는 임시저장소 (세션 등을 임시 저장)
		
		rq.init(); // rq객체를 컨테이너를 통해 생성했지만 이렇게 사용을 한번이라도 해야 그 객체가 살아있음 (아니면 죽음)
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
}
