<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name }"/>

<%@ include file="../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<div class="mb-2">
				${articlesCnt}개의 글
			</div>
			<div class="table-box-type-1">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>작성일</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}"> <!-- for(Article article : articles) 랑 똑같은말 -->
						<tr>
							<td>${article.id}</td>
							<td>${article.regDate.substring(2, 16)}</td>
							<td><a class="hover:underline" href="detail?id=${article.id }">${article.title}</a></td>
							<td>${article.nickName}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${rq.loginedMemberId != 0}">
				<div class="mt-2 flex justify-end">
					<a href="write" class="btn btn-outline btn-success">글 작성</a>
				</div>
			</c:if>
				
			<div class="mt-4 flex justify-center">
				<div class="join">
				
					<c:if test="${startPage > 1 }">
						<a class="join-item btn" href="?boardId=${board.id }&page=${startPage-1}">이전</a>
					</c:if>
					
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="join-item btn ${page == i ? 'btn-active' : ' '}" href="?boardId=${board.id }&page=${i }">${i }</a>
					</c:forEach>
					
					<c:if test="${endPage * pageLen <= articlesCnt}">
						<a class="join-item btn" href="?boardId=${board.id }&page=${endPage+1}">다음</a>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>