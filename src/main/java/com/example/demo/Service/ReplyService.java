package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BoardDao;
import com.example.demo.dao.ReplyDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Reply;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String replyText) {
		
		replyDao.writeReply(loginedMemberId, relTypeCode, relId, replyText);
		
	}

	public List<Reply> getReplies(String relTypeCode, int relId) {
		return replyDao.getReplies(relTypeCode, relId);
	}

	public void deleteReply(int id) {
		replyDao.deleteReply(id);
		
	}

	public Reply getReply(int id) {
		return replyDao.getReply(id);
		
	}

	public void doModify(int id, String replyText) {
		replyDao.doModify(id, replyText);
		
	}
}
