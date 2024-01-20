<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입"/>

<%@ include file="../common/header.jsp" %>

<section class="mt-8">
		<div class="container mx-auto">
			<form action="/usr/member/doJoin" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200">
						</colgroup>
						<tbody>
							<tr>
								<th>아이디</th>
								<td><input type="text" name="loginId" placeholder="아이디를 입력해주세요"/></td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요"/></td>
							</tr>
							<tr>
								<th>이름</th>
								<td><input type="text" name="name" placeholder="이름을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td><input type="text" name="nickName" placeholder="닉네임을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="tel" name="phoneNum"/></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input type="email" name="email"/></td>
							</tr>
							<tr>
								<td colspan="2"><button>회원가입</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
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