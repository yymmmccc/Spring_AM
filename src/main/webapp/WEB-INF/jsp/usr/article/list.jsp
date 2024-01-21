<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name }"/>

<%@ include file="../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<div class="mb-2 flex justify-between">
				<div>
					<span>${ap.articlesCnt}개의 글</span>
				</div>
				<div>
					<form method="GET">
							<select data-value="${searchType}" name="searchType">
								<option value="title">제목</option>
								<option value="body">내용</option>
								<option value="titleBody">제목 + 내용</option>
							</select>
						<input class="mt-2" type="text" name="searchKeyword" value="${searchKeyword }" placeholder="검색어를 입력하세요."/>
						<input type="hidden" name="boardId" value="${board.id }"/>
						<button>검색</button>
					</form>
				</div>
			</div>
			<div class="table-box-type-1">
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}"> 
						<tr>
							<td>${article.id}</td>
							<td><a class="hover:underline" href="detail?id=${article.id }">${article.title}</a></td>
							<td>${article.nickName}</td>
							<td>${article.regDate.substring(12, 16)}</td>
							<td>${article.hit }</td>
							<td>${article.sumReactionPoint }
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
			
			<c:if test="${rq.loginedMemberId != 0}">
				<div class="mt-2 flex justify-end">
					<a href="write" class="btn btn-outline btn-success">글 작성</a>
				</div>
			</c:if>
				
			<div class="mt-4 flex justify-center">
				<div class="join">
					<!--  &searchType=${searchType}&searchKeyword=${searchKeyword} 이거 변수로 코드 정리하기-->
					<c:if test="${ap.startPage > 1 }">
						<a class="join-item btn" href="?boardId=${board.id }&page=${ap.startPage-1}&searchType=${searchType}&searchKeyword=${searchKeyword}">이전</a>
					</c:if>
					
					<c:forEach begin="${ap.startPage }" end="${ap.endPage }" var="i">
						<a class="join-item btn ${ap.page == i ? 'btn-active' : ' '}" href="?boardId=${board.id }&page=${i }&searchType=${searchType}&searchKeyword=${searchKeyword}">${i }</a>
					</c:forEach>
					
					<c:if test="${ap.endPage * ap.pageArticles <= ap.articlesCnt}">
						<a class="join-item btn" href="?boardId=${board.id }&page=${ap.endPage+1}&searchType=${searchType}&searchKeyword=${searchKeyword}">다음</a>
					</c:if>
				</div>
			</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>