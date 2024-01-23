package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller  // 컨트롤러의 역할을 할 수 있게해줌
// 사용자가 요청을 보냈을 때 출발하는 곳이 controller
public class UsrReplyController {
	
	ReplyService replyService;
	Rq rq;
	
	
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite") // 댓글 추가 기능
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String replyText) {
		
		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, replyText);
		
		return Util.jsReplace("댓글 작성 성공!", String.format("/usr/article/detail?id=%d", relId));
	}
	
	
	@RequestMapping("/usr/reply/doDelete") // 댓글 삭제 기능
	@ResponseBody
	public String doDelete(int id) {
		
		Reply reply = replyService.getReply(id);
		
		if(reply == null) {
			return Util.jsHistoryBack("해당 댓글은 존재하지않습니다.");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) { // 해당 댓글에 권한이없는경우
			return Util.jsHistoryBack("해당 권한이 없습니다.");
		}
		
		replyService.deleteReply(id);
		
		return Util.jsReplace("댓글이 삭제되었습니다!", String.format("/usr/article/detail?id=%d", reply.getRelId()));
	}
	
	@RequestMapping("/usr/reply/doModify") // 댓글 수정기능
	@ResponseBody
	public String doModify(int id, String replyText) {
		
		Reply reply = replyService.getReply(id);
		
		if(reply == null) {
			return Util.jsHistoryBack("해당 댓글은 존재하지않습니다.");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) { // 해당 댓글에 권한이없는경우
			return Util.jsHistoryBack("해당 권한이 없습니다.");
		}
		
		replyService.doModify(id, replyText);
		
		return Util.jsReplace("댓글이 수정되었습니다.", String.format("/usr/article/detail?id=%d", reply.getRelId()));
	}
	
	@RequestMapping("/usr/reply/getReplyContent") // 해당 댓글정보를 객체화 해서 리턴 -> jsp에서는 그걸 갖고 댓글 수정창에 보여줌
	@ResponseBody
	public ResultData<Reply> getReplyContent(int id) {
		
		Reply reply = replyService.getReply(id);
		
		if(reply == null) {
			return ResultData.from("F-1", "해당 댓글은 존재하지 않습니다.");
		}
		
		return ResultData.from("S-1", "댓글 정보 조회 성공", "reply", reply);
	}
}
