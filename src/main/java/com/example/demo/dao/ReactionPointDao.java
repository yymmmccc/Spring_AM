package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointDao {

	@Select("""
			SELECT IFNULL(SUM(point), 0) AS sumReactionPoint
				FROM reactionPoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	ReactionPoint getReactionPoint(String relTypeCode, int relId, int loginedMemberId);
	
	
	@Insert(""" 
			INSERT INTO reactionPoint
			SET memberId = #{loginedMemberId}
				, relTypeCode = #{relTypeCode}
				, relId = #{relId}
				, point = #{point}
				, regDate = NOW()
			
			""")
	void doInsertReactionPoint(String relTypeCode, int relId, int loginedMemberId, int point);


	@Delete("""
			DELETE FROM reactionPoint 
			WHERE memberId = #{loginedMemberId}
			AND relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			""")
	void doDeleteReactionPoint(String relTypeCode, int relId, int loginedMemberId, int point);

}
