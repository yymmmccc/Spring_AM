package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	public Article getArticleById(int id);
	
	public List<Article> getArticles(int boardId, int pageStart, int currentPages);
	
	public void writeArticle(String title, String body, int memberId, int boardId);
	
	public void modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastArticle();

	@Select("SELECT COUNT(*) FROM article WHERE boardId = #{boardId}")
	public int getArticlesCnt(int boardId);
}
