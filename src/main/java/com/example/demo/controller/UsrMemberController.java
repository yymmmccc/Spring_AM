package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	MemberService memberService;
	
	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickName, String phoneNum, String email) {

		if(Util.empty(loginId)) {
			return "아이디를 입력해주세요";
		}
		if(Util.empty(loginPw)) {
			return "비밀번호를 입력해주세요";
		}
		if(Util.empty(name)) {
			return "이름을 입력해주세요";
		}
		if(Util.empty(nickName)) {
			return "닉네임을 입력해주세요";
		}
		if(Util.empty(phoneNum)) {
			return "전화번호를 입력해주세요";
		}
		if(Util.empty(email)) {
			return "이메일을 입력해주세요";
		}
		
		int id = memberService.doJoin(loginId, loginPw, name, nickName, phoneNum, email);
		
		if(id == -1) {
			return Util.f("이미 사용중인 아이디(%s)입니다.", loginId);
		}
		
		if(id == -2) {
			return Util.f("(%s)는 중복된 닉네임입니다.", nickName);
		}
		
		if(id == -3) {
			return "이미 사용중인 이름과 이메일입니다.";
		}
		
		return memberService.getMemberById(id);
	}

}
