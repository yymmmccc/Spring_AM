package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Service
public class ArticleService {
	private ArticleDao articleDao;
	
	@Autowired
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public int writeArticle(String title, String body, int memberId) {
		articleDao.writeArticle(title, body, memberId);
		 
		return getLastArticle();
	}
	
	public ResultData modifyArticle(HttpSession session, int id, String title, String body) {
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-1", "로그인 후 이용해주세요.");
		}
		
		Article article = getArticleById(id);
		
		int loginedMemberId = (int)session.getAttribute("loginedMemberId");
		int memberId = article.getMemberId();
		
		if(loginedMemberId != memberId) {
			return ResultData.from("F-1", "해당 게시물에 대한 권한이 없습니다.");
		}
		
		articleDao.modifyArticle(id, title, body);
		
		article = articleDao.getArticleById(id);
		
		return ResultData.from("S-1", "게시글이 수정되었습니다.", article);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public int getLastArticle() {
		return articleDao.getLastArticle();
	}
}
