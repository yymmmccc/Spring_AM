package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

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
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {
		
		if(session.getAttribute("loginedMemberId") != null) { // 세션내용을 보았는데 데이터가 있는경우(이미 로그인 된 경우)
			return ResultData.from("F-A", "이미 로그인되어 있습니다.");
		}

		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		
		if(Util.empty(loginPw)) {
			return ResultData.from("F-1", "비밀번호를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return ResultData.from("F-1", "아이디가 존재하지 않습니다.");
		}
		
		if(!member.getLoginPw().equals(loginPw)) {
			return ResultData.from("F-1", "정보가 일치하지 않습니다.");
		}
		
		// HttpSession session 은 spring이 알아서 session의 정보들을 불러옴
		session.setAttribute("loginedMemberId", member.getId());
		// 로그인 성공하면 그 정보를 세션에 저장함.
		//session.setAttribute("nickName", member.getNickName());
				
		return ResultData.from("S-1", member.getNickName() + "님 환영합니다.");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		if(session.getAttribute("loginedId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		session.removeAttribute("loginedId");
		
		return ResultData.from("S-1", "로그아웃 되었습니다.");
	}
	
}
