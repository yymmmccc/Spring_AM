package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	public Article getArticleById(int id);
	
	public List<Article> getArticles();
	
	public void writeArticle(String title, String body);
	
	public void modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastArticle();
}
