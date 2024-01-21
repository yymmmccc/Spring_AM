<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="detail"/>

<%@ include file="../common/header.jsp" %>	

	<section class="mt-8">
		<div class="container mx-auto">
			<div class="table-box-type-1">
				<table>
					<colgroup>
						<col width="200">
					</colgroup>
					<tbody>
						<tr>
							<th>번호</th>
							<td>${article.id }</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>${article.regDate }</td>
						</tr>
						<tr>
							<th>수정일</th>
							<td>${article.updateDate }</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.nickName }</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${article.title }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>${article.body }</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td><span id="articleDetail_increaseHitCnt">${article.hit }</span></td>
						</tr>
						<tr>
							<c:set var="point" value="0"/>
							<th>좋아요</th>
							<td>${point }</td>
						<c:forEach var="reactionPoint" items="${reactionPoints}">
							<td>
								<c:set var="point" value="${point + reactionPoint.point}"/>
							</td>
						</c:forEach>
						</tr>
						<tr>
							<th>싫어요</th>
							<c:if test="${reactionPoint.point == -1 }">
							<td>${reactionPoint.point }</td>
							</c:if>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<button class="btn btn-outline btn-success" onclick="history.back()">뒤로가기</button>
			<c:if test="${article.memberId == rq.loginedMemberId}">
				<a class="btn btn-outline btn-info" href="modify?id=${article.id}">수정</a>
				<a class="btn btn-outline btn-error" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
			</div>
			
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>