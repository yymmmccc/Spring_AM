package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.ArticleService;
import com.example.demo.Service.BoardService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Rq;

@Controller // 웹 화면에 보여줄 수 있게 해줌
public class UsrArticleController {
	
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;
	
	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
		
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		
		return "/usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, int boardId) {
		
		if(Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		int id = articleService.writeArticle(title, body, rq.getLoginedMemberId(), boardId);
		
		Article article = articleService.getArticleById(id);
		
		return Util.jsReplace("게시글이 작성되었습니다.", Util.f("/usr/article/detail?id=%d", article.getId()));
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model,
			@RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "1") int page){
		
		if(page <= 0) {
			return rq.jsReturnOnview("페이지 번호가 올바르지 않습니다.");
		}
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			return rq.jsReturnOnview("존재하지 않는 게시판입니다.");
		}
		
		int articlesCnt = articleService.getArticlesCnt(boardId); // 현재게시판의 총 게시글수
		
		int currentPages = 10;   // 한 페이지당 게시글 갯수 ex. 10개 보여줄거
		int pageLen = 10;		// 밑에 보여줄 페이지 갯수.
		
		int endPage = (int) (Math.ceil((double)page / pageLen) * pageLen);
		int startPage = (endPage - pageLen) + 1; // startPage는 1, 11, 21... 의 값을 갖는다. 
		// page = 21페이지, 밑에 페이지번호갯수 = 10 이면, 끝페이지는 30 페이지
		
		int totalPage = (int) Math.ceil((double)articlesCnt / currentPages); // 전체 게시글 갯수 / 한 페이지당게시글 갯수
		
		if(page > totalPage) {
			return rq.jsReturnOnview("페이지 번호를 너무 많이 작성하셨습니다.");
		}
		
		if(endPage > totalPage) { // 오류를 방지하기 위한 메서드
			endPage = totalPage;
		}

		List<Article> articles = articleService.getArticles(boardId, page, currentPages);
		
		model.addAttribute("articles", articles); // 조건에 부합한 게시글수 DB에서 가져오기
		model.addAttribute("board", board); // 공지사항, 자유게시판인지 구분하기 위함.
		model.addAttribute("articlesCnt", articlesCnt); // 공지사항, 자유게시판 등 해당 게시글의 전체 글 갯수 파악 변수
		model.addAttribute("pageLen", pageLen);
		model.addAttribute("page", page); // 현재 몇 페이지인지 jsp가 알아야 그 페이지를 그려줌
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id){ // Object 타입은 최상위 타입 : 객체, 스트링 모든거 다 리턴가능
		
		Article article = articleService.getArticleById(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			//return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
			return rq.jsReturnOnview(Util.f("%d번 게시물은 존재하지않습니다.", id));
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return rq.jsReturnOnview("해당 게시물에 대한 권한이없습니다.");
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
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
	public String doDelete(int id) {
		
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
