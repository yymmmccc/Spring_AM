package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {

	@Insert("""
			INSERT INTO reply
			SET memberId = #{loginedMemberId}
			, relTypeCode = #{relTypeCode}
			, relId = #{relId}
			, replyText = #{replyText}
			, regDate = NOW()
			, updateDate = NOW()
			""")
	public void writeReplay(int loginedMemberId, String relTypeCode, int relId, String replyText);

	@Select("""
			SELECT R.*, M.nickName 
				FROM reply AS R 
				INNER JOIN member AS M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
			""")
	public List<Reply> getReplies(String relTypeCode, int relId);
}
