package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ReactionPointDao;
import com.example.demo.vo.ReactionPoint;


@Service
public class ReactionPointService {
	
	private ReactionPointDao reactionPointDao;
	
	public ReactionPointService(ReactionPointDao reactionPointDao) {
		this.reactionPointDao = reactionPointDao;
	}

	public ReactionPoint getReactionPoint(String relTypeCode, int relId, int loginedMemberId) {
		return reactionPointDao.getReactionPoint(relTypeCode, relId, loginedMemberId);
	}

	public void doInsertReactionPoint(String relTypeCode, int relId, int loginedMemberId, int point) {
		reactionPointDao.doInsertReactionPoint(relTypeCode, relId, loginedMemberId, point);
	}

	public void doDeleteReactionPoint(String relTypeCode, int relId, int loginedMemberId, int point) {
		reactionPointDao.doDeleteReactionPoint(relTypeCode, relId, loginedMemberId, point);
		
	}
	
}
