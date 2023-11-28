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
	
	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticleById(int id);
	
	//@Insert
	public Article writeArtice(String title, String body);
	
	@Update("UPDATE article SET updateDate = NOW(), title = #{title}, body = #{body} WHERE id = #{id}")
	public void modifyArticle(int id, String title, String body);

	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles();
}
