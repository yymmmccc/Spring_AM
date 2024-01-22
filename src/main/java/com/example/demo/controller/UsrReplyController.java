package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ReplyService;
import com.example.demo.util.Util;
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
	
	@RequestMapping("/usr/reply/doWrite") // 도메인 주소를 매핑해줌
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String replyText) {
		
		replyService.writeReplay(rq.getLoginedMemberId(), relTypeCode, relId, replyText);
		
		return Util.jsReplace("댓글 작성 성공!", String.format("/usr/article/detail?id=%d", relId));
	}
}
