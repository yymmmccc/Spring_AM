package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	MemberService memberService;
	
	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickName, String phoneNum, String email) {
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if(Util.empty(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요");
		}
		if(Util.empty(name)) {
			return ResultData.from("F-1", "이름을 입력해주세요");
		}
		if(Util.empty(nickName)) {
			return ResultData.from("F-1", "닉네임을 입력해주세요");
		}
		if(Util.empty(phoneNum)) {
			return ResultData.from("F-1", "전화번호를 입력해주세요");
		}
		if(Util.empty(email)) {
			return ResultData.from("F-1", "이메일을 입력해주세요");
		}
		
		ResultData<Member> resultData = memberService.doJoin(loginId, loginPw, name, nickName, phoneNum, email);
		
		return resultData;
	}

}
