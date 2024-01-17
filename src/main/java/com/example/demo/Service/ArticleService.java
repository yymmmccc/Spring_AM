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

	public List<Article> getArticles(int boardId, int page, int currentPages) {
		
		int pageStart = (page - 1) * currentPages;  
		// ex. 1페이지는 sql에서 0부터 가져오기, 2페이지는 sql에서 10부터 가져오기, 3페이지는 sql에서 20
		
		return articleDao.getArticles(boardId, pageStart, currentPages);
	}
	
	public int getLastArticle() {
		return articleDao.getLastArticle();
	}

	public int getArticlesCnt(int boardId) {
		return articleDao.getArticlesCnt(boardId);
	}
}
