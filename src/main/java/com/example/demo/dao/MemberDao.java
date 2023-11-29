package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberDao {

	@Insert("""
			INSERT INTO member
			SET regDate = NOW()
				, updateDate = NOW()
				, loginId = #{loginId}
				, loginPw = #{loginPw}
				, name = #{name}
				, nickName = #{nickName}
				, phoneNum = #{phoneNum}
				, email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickName, String phoneNum, String email);
	
	@Select("SELECT * FROM member WHERE loginId = #{loginId}")
	public Member getMemberByLoginId(String loginId);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInertId();

	@Select("SELECT * FROM member WHERE id = #{id}")
	public Member getMemberById(int id);
	
	@Select("SELECT * FROM member WHERE nickName = #{nickName}")
	public Member getMemberNickName(String nickName);

	@Select("SELECT * FROM member WHERE name = #{name} and email = #{email}")
	public Member getMemberEmail(String name, String email);
	
}
