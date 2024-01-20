package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {
	MemberDao memberDao;
	
	@Autowired
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickName, String phoneNum, String email) {
		
		Member existsMember = getMemberByLoginId(loginId);
		
		if(existsMember != null) { // 우리가 가입하려는 아이디가 이미 사용중일때
			return ResultData.from("F-1", "이미 사용중인 아이디입니다.");
		}
		
		existsMember = getMemberNickName(nickName);
		
		if(existsMember != null) {
			return ResultData.from("F-1", "이미 사용중인 닉네임입니다.");
		}
		
		existsMember = getMemberEmail(name, email);
		
		if(existsMember != null) {
			return ResultData.from("F-1", "이미 사용중인 이메일입니다.");
		}
		
		memberDao.doJoin(loginId, loginPw, name, nickName, phoneNum, email);
		
		Member member = getMemberById(getLastInsertId());
		return ResultData.from("S-1", "회원가입 성공 !");
	}
	
	private Member getMemberNickName(String nickName) {
		return memberDao.getMemberNickName(nickName);
	}

	private Member getMemberEmail(String name, String email) {
		return memberDao.getMemberEmail(name, email);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public int getLastInsertId() {
		return memberDao.getLastInertId();
	}


}


