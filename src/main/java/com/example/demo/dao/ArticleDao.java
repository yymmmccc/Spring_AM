package com.example.demo.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {
	
	public Article getArticleById(int id);
	
	public List<Article> getArticles(int boardId, String searchType, String searchKeyword, int pageStart, int pageArticles);
	
	public void writeArticle(String title, String body, int memberId, int boardId);
	
	public void modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastArticle();

	public int getArticlesCnt(int boardId, String searchType, String searchKeyword);
	
	@Update("UPDATE article SET hit = hit + 1 where id = #{id}")
	public void articleHitInc(int id);
	
}
