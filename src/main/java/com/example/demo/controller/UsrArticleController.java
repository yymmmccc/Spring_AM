package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ArticleService;
import com.example.demo.vo.Article;

@Controller // 웹 화면에 보여줄 수 있게 해줌
public class UsrArticleController {
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
		
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {

		Article article = articleService.writeArticle(title, body);
		
		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles(){
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id){ // Object 타입은 최상위 타입 : 객체, 스트링 모든거 다 리턴가능
		Article foundArticle = articleService.getArticleById(id);
		if(foundArticle == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		return foundArticle;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle == null) {
			return id + "번 게시물이 존재하지않습니다.";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return id + "번 게시글이 수정되었습니다.";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle == null) {
			return id + "번 게시물이 존재하지않습니다.";
		}
	
		articleService.deleteArticle(id);
		
		return id + "번 게시글이 삭제되었습니다";
	}
}
