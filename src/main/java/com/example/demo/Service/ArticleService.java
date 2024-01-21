package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public int writeArticle(String title, String body, int memberId, int boardId) {
		articleDao.writeArticle(title, body, memberId, boardId);
		 
		return getLastArticle();
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public List<Article> getArticles(int boardId, String searchType, String searchText, int page, int pageArticles) {
		
		int pageStart = (page - 1) * pageArticles;  
		// ex. 1페이지는 sql에서 0부터 가져오기, 2페이지는 sql에서 10부터 가져오기, 3페이지는 sql에서 20
		
		return articleDao.getArticles(boardId, searchType, searchText, pageStart, pageArticles);
	}
	
	public int getLastArticle() {
		return articleDao.getLastArticle();
	}

	public int getArticlesCnt(int boardId, String searchType, String searchKeyword) {
		return articleDao.getArticlesCnt(boardId, searchType, searchKeyword);
	}
	
	public void articleHitInc(int id) {
		articleDao.articleHitInc(id);  // 조회수를 하나 올리고 그 값을 affected에 저장
	}
}
