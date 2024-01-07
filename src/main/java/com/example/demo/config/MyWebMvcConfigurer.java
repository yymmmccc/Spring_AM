package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	
	private BeforeActionInterceptor beforeActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	
	@Autowired // 의존성주입. before... 의 객체를 new(생성)하지 않아도 매개변수로 받아와 바로 사용 가능
	public MyWebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor, NeedLoginInterceptor needLoginInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
		// addPathPatterns (경로의 모양을 추가한다) /** = 모든 요청이 들어오면 판단한다는 말
		// excludePathPatterns resource 경로는 정적(css)라서 제어할거야 (검증필요 x)
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/doAdd")
				.addPathPatterns("usr/article/doModify").addPathPatterns("usr/article/doDelete");
	}
	
	
}
