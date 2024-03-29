<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.6.0/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>

<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css"/>

<!--  -->
<script src="/resource/common.js" defer='defer'></script>
 
<title>${pageTitle }</title>
</head>
<body>
	<div class="h-20 flex container mx-auto text-4xl">
		<a class="px-3 flex items-center" href="/"><span>로고</span></a>
		<div class="flex-grow"></div>
		<ul class="flex">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/">HOME</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=1">NOTICE</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=2">FREE</a></li>
			<c:if test="${rq.getLoginedMemberId() == 0}">
			<!-- rq는 요청(req)에 저장되어 있음. 요청에 저장된것들은 바로 꺼내서 사용 가능 (키로 꺼내는거임-->
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/login">LOGIN</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/join">JOIN</a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0}">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/doLogout">LOGOUT</a></li>
			</c:if>
		</ul>
	</div>
	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle}</h1>
		</div>
	</section>