package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // toString, getter, setter 메서드를 자동으로 만들어줌
@AllArgsConstructor // 사용자가 도메인에 값을 요청하면 알아서 생성자를 만듦

public class ArticlePage {
	
	private int endPage;	  // 밑에 보여줄 마지막 페이지
	private int startPage;	  // 밑에 보여줄 처음 페이지
	private int totalPage;    // 전체 페이지 개수
	private int page;		  // 현재페이지
	private int pageArticles = 10;// 한 페이지당 게시글 수 
	private int pageLen = 10;	  // 밑에 보여줄 페이지 개수
	private int articlesCnt;
	
	public ArticlePage(int page, int articlesCnt){
		this.articlesCnt = articlesCnt;
		this.page = page;
	}
	
	public void CalcData() { // 계산을 해서 변수들에 값을 넣어줌
		
		this.endPage = (int) (Math.ceil((double) this.page / pageLen) * pageLen);
		this.startPage = (endPage - pageLen) + 1; // startPage는 1, 11, 21... 의 값을 갖는다.
		this.totalPage = (int) Math.ceil((double)articlesCnt / pageArticles);
		
		if(endPage > totalPage) { // 오류를 방지하기 위한 메서드
			endPage = totalPage;
		}
		
	}
}
