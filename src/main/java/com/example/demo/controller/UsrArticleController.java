package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller // 웹 화면에 보여줄 수 있게 해줌
public class UsrArticleController {
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		// HttpServletRequest은 요청과 동시에 만들어짐 (그 안에는 세션 등의 데이터가 담겨있음)
		Rq rq = (Rq)req.getAttribute("rq"); 
		// 이전에는 new Rq()를 통해 객체를 메소드들한테 매번 생성했지만
		// 이제는 contoroller 가기전 핸들러에서 rq객체를 생성하고 그걸 req(요청임시저장소에 저장)해서 불러다가 쓰고 있음
		
		if(Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요");
		}
		
		int id = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.from("S-1", "게시물 작성 성공!", article);
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model){
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		// jsp에 articles를 넘기지 않아도 model에 값을 넣으면 jsp는 그 값을 알고있음. 
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id){ // Object 타입은 최상위 타입 : 객체, 스트링 모든거 다 리턴가능
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		model.addAttribute("article", article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return Util.jsHistoryBack("해당 게시물은 존재하지 않습니다.");
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack("해당 게시물에 권한이 없습니다.");
		}
		
		articleService.modifyArticle(id, title, body);
		return Util.jsReplace(String.format("%s번 게시글이 수정되었습니다.", id), String.format("detail?id=%d", id));
		// "usr/article/modify";
		
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return Util.jsHistoryBack("해당 게시글이 존재하지 않습니다.");
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack("해당 게시물에 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(String.format("%d번 게시글이 삭제되었습니다.", id), "list");
	}
}
