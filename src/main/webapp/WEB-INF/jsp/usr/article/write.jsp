<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="write"/>

<%@ include file="../common/header.jsp" %>	
	<section class="mt-8">
		<div class="container mx-auto">
		<form action="/usr/article/doWrite" method="GET">
			<div class="table-box-type-1">
				<table>
					<colgroup>
						<col width="200">
					</colgroup>
					<tbody>
						<tr>
							<th>게시판</th>
							<td>
								<label>
									<input type="radio" name="boardId" value="1" checked/>&nbsp;공지사항
								</label>
								&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="boardId" value="2"/>&nbsp;자유게시판
								</label>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" placeholder="제목"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="body" cols="30" rows="10" placeholder="내용을 입력하세요."></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<button class="btn btn-outline btn-success" onclick="history.back()">뒤로가기</button>
			<button class="btn btn-outline btn-success">글 작성</button>
			</div>
			</form>
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>