package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

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
	
	public Article writeArticle(String title, String body) {
		return articleDao.writeArtice(title, body);
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
}
