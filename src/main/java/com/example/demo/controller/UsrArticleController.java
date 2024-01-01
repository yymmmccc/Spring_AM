package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller // 웹 화면에 보여줄 수 있게 해줌
public class UsrArticleController {
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
		
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession session, String title, String body) {
		
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인 후 이용가능합니다.");
		}
		
		if(Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요");
		}
		
		int memberId = (int)session.getAttribute("loginedMemberId");
		
		int id = articleService.writeArticle(title, body, memberId);
		
		Article article = articleService.getArticleById(id);
		//System.out.println(id);
		return ResultData.from("S-1", "게시물 작성 성공!", article);
	
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles(){
		
		List<Article> articles = articleService.getArticles();
		
		if(articles.size() == 0) {
			return ResultData.from("F-1", "게시글이 존재하지않습니다.");
		}
		
		return ResultData.from("S-1", "게시글 전체보기", articles);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id){ // Object 타입은 최상위 타입 : 객체, 스트링 모든거 다 리턴가능
		Article foundArticle = articleService.getArticleById(id);
		if(foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Util.f("%d번 게시물입니다", id), foundArticle);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle == null) {
			return ResultData.from("F-1", "해당 게시물이 없습니다.");
		}
		
		return articleService.modifyArticle(session, id, title, body);
		
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle == null) {
			return ResultData.from("F-1", "해당 게시글이 존재하지 않습니다.");
			
		}
		
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");
		int memberId = foundArticle.getMemberId();
		
		if(loginedMemberId != memberId) {
			return ResultData.from("F-1", "해당 게시물에 대한 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		
		
		return ResultData.from("S-1", "해당 게시글이 삭제되었습니다.");
	}
}
