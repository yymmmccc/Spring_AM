package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ReactionPointService;
import com.example.demo.util.Util;
import com.example.demo.vo.ReactionPoint;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller  // 컨트롤러의 역할을 할 수 있게해줌
// 사용자가 요청을 보냈을 때 출발하는 곳이 controller
public class UsrReactionPointController {
	
	private ReactionPointService reactionPointService;
	private Rq rq;
	
	@Autowired
	UsrReactionPointController(ReactionPointService reactionPointService, Rq rq){
		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reactionPoint/getReactionPoint")
	@ResponseBody
	public ResultData<ReactionPoint> getReactionPoint(String relTypeCode, int relId) {
		
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(relTypeCode, relId, rq.getLoginedMemberId());
		
		return ResultData.from("s-1", "리액션 정보 조회성공", "reactionPoint", reactionPoint);
	}
	
	@RequestMapping("/usr/reactionPoint/doInsertReactionPoint")
	@ResponseBody
	public String doInsertReactionPoint(String relTypeCode, int relId, int point) {
		
		ReactionPoint reactionPoint = reactionPointService.getReactionPoint(relTypeCode, relId, rq.getLoginedMemberId());
		
		if(reactionPoint.getSumReactionPoint() != 0) { 
			// detail에서 추천을 예전에 추천을 누르고 비추천을 누르려할 때 
			// -> 비추천에 insert문이 실행되기전에 기존 point 데이터가 있으면 삭제하고 인서트
			reactionPointService.doDeleteReactionPoint(relTypeCode, relId, rq.getLoginedMemberId(), point);
		}
		
		reactionPointService.doInsertReactionPoint(relTypeCode, relId, rq.getLoginedMemberId(), point);
	
		if(point == 1) {
			return Util.jsReplace("추천!", Util.f("../article/detail?id=%d", relId));
		}
		
		else {
			return Util.jsReplace("비 추천!", Util.f("../article/detail?id=%d", relId));
		}
		
	}
	
	@RequestMapping("/usr/reactionPoint/doDeleteReactionPoint")
	@ResponseBody
	public String doDeleteReactionPoint(String relTypeCode, int relId, int point) {
		
		reactionPointService.doDeleteReactionPoint(relTypeCode, relId, rq.getLoginedMemberId(), point);
	
		if(point == 1) {
			return Util.jsReplace("추천 취소!", Util.f("../article/detail?id=%d", relId));
		}
		else {
			return Util.jsReplace("비 추천 취소!", Util.f("../article/detail?id=%d", relId));
		}
		
	}
}
