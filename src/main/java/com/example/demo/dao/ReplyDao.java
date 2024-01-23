package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String replyText);

	@Select("""
			SELECT R.*, M.nickName 
				FROM reply AS R 
				INNER JOIN member AS M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
			""")
	public List<Reply> getReplies(String relTypeCode, int relId);

	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
			""")
	public void deleteReply(int id);

	@Select("""
			SELECT * FROM
			reply
			WHERE id = #{id}
			""")
	public Reply getReply(int id);

	@Update("""
			UPDATE reply
			SET replyText = #{replyText}
			, updateDate = NOW()
			WHERE id = #{id}
			""")
	public void doModify(int id, String replyText);
}
