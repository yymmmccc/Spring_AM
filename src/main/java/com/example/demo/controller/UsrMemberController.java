package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
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
	public String doJoin(String loginId, String loginPw, String name, String nickName, String phoneNum, String email) {
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요.");
		}
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");
		}
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요.");
		}
		if(Util.empty(nickName)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		if(Util.empty(phoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요.");
		}
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요.");
		}
		
		ResultData<Member> resultData = memberService.doJoin(loginId, loginPw, name, nickName, phoneNum, email);
		
		return Util.jsReplace(resultData.getMsg(), "login");
	}
	
	@RequestMapping("/usr/member/login")
	public String login(HttpSession session, String loginId, String loginPw) {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		
		if(rq.getLoginedMemberId() != 0) { // 세션내용을 보았는데 데이터가 있는경우(이미 로그인 된 경우)
			return Util.jsHistoryBack("로그아웃 후 이용해주세요.");
		}

		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요.");
		}
		
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Util.jsHistoryBack("아이디가 존재하지않습니다.");
		}
		
		if(!member.getLoginPw().equals(loginPw)) {
			return Util.jsHistoryBack("비밀번호가 일치하지않습니다.");
		}
		
		rq.login(member);
		// HttpSession session 은 spring이 알아서 session의 정보들을 불러옴
		//session.setAttribute("loginedMemberId", member.getId());
		// 로그인 성공하면 그 정보를 세션에 저장함.
		//session.setAttribute("nickName", member.getNickName());
				
		return Util.jsReplace(Util.f("%s님 환영합니다.", member.getNickName()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		rq.logout();
		
		return Util.jsReplace("로그아웃되었습니다.", "/");
	}
	
}
