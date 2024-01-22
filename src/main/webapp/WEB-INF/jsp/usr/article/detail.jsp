<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="detail"/>

<%@ include file="../common/header.jsp" %>	

<script>
	function getReactionPoint(){
		
		$.get('../reactionPoint/getReactionPoint', { // detail 현재 페이지에 좋아요, 싫어요 눌렀는지 알 수 있는 메서드
			relTypeCode : 'article',
			relId : ${article.id}
			// 파라미터 세팅하는 공간
		}, function(data){ // /reactionPoint/getReactionPoint 메서드에서 받는 리턴값
		
			if(data.data1.sumReactionPoint > 0){ // 어떤 사용자가 좋아요를 누른경우
				let goodBtn = $('#goodBtn');
				goodBtn.removeClass('btn-outline');
				goodBtn.attr('href', '../reactionPoint/doDeleteReactionPoint?relTypeCode=article&relId=${article.id }&point=1')
			}
			else if( data.data1.sumReactionPoint < 0){ // 어떤 사용자가 싫어요를 누른경우
				let badBtn = $('#badBtn');
				badBtn.removeClass('btn-outline');
				badBtn.prop('href', '../reactionPoint/doDeleteReactionPoint?relTypeCode=article&relId=${article.id }&point=-1')
			}
			
		}, 'json') // 어떤 타입으로 리턴을 받을지
	}
	
	getReactionPoint();
</script>

	<section class="mt-8">
		<div class="container mx-auto pb-10 border-bottom-line">
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
							<td>${article.getForPrintBody() }</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td><span id="articleDetail_increaseHitCnt">${article.hit }</span></td>
						</tr>
						<tr>
							<th>추천</th>
							<td>
								<div>
									<a id="goodBtn" class="btn btn-outline btn-success btn-xs" href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=1">좋아요👍</a>
									<span class="ml-2">${article.goodReactionPoint }</span>
								</div>
								<div class="mt-2">
									<a id="badBtn" class="btn btn-outline btn-error btn-xs" href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=-1">싫어요👎</a>
									<span class="ml-2">${article.badReactionPoint }</span>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="pt-8">
			<button class="btn btn-outline btn-success" onclick="history.back()">뒤로가기</button>
			<c:if test="${article.memberId == rq.loginedMemberId}">
				<a class="btn btn-outline btn-info" href="modify?id=${article.id}">수정</a>
				<a class="btn btn-outline btn-error" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
			</div>
		</div>
	</section>
	
	<section class="mt-3 text-xl">
		<div class="container mx-auto px-3">
			<h1>댓글</h1>
			<c:forEach var="reply" items="${replies }">
				<div class="text-base py-4 pl-16 border-bottom-line">
					<div class="font-bold"><span>${reply.nickName }</span></div>
					<div class="my-1 text-lg"><span>${reply.getForPrintText() }</span></div>
					<div class="text-xs text-gray-400"><span>${reply.updateDate }</span></div>
				</div>
			</c:forEach>
		<c:if test="${rq.loginedMemberId != 0}">
			<form action="../reply/doWrite" method="GET">
				<input type="hidden" name="relTypeCode" value="article"/>
				<input type="hidden" name="relId" value="${article.id }"/>
				<div class="mt-4 border border-red-400 rounded-lg p-4 text-base">
					<div class="mb-2"><span>${rq.loginedMember.nickName }</span></div>
					<textarea class="textarea textarea-bordered w-full" name="replyText" placeholder="댓글을 남겨보세요."></textarea>
					<div class="mt-1 flex justify-end "><button class="btn btn-outline btn-info btn-sm">등록</button></div>
				</div>
			</form>
		</c:if>
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>