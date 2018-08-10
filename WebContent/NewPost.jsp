<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/style.css" rel="stylesheet" type="text/css">
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="./js/jQuery.min.js"></script>
		<title>新規投稿</title>
	</head>

	<header>

	<c:import url="header.jsp" />

	<div class="header-bottom">
		<nav style="position: absolute;">
			<ul class="nav-list">
				<a href="./"><img src="./fonts/home.png" width="15"> HOME　</a>
				<img src="./fonts/right.png" width="10">　新規投稿
			</ul>
		</nav>
		<nav style="text-align: right;">
			<ul class="nav-list">
				<li class="nav-list-item"><a href="logout"><img src="./fonts/logout.png" width="20"> logout</a></li>
			</ul>
		</nav>
	</div>
	</header>

	<body>
	<div class="top-box"></div>
		<div class="container">
			<a href="./">戻る</a>
			<div class="row">
				<div class="col-xs-3">
					<h1 style="margin-top: 30px;">新規投稿</h1>
				</div>
				<div class="col-xs-6"></div>
				<div class="col-xs-3" style="position: absolute; right: 150px; top: 210px;">
					<li><label>所属部署：</label>${loginUser.branchName }　${loginUser.positionName}</li>
					<li><label>ユーザー名：</label>${loginUser.name}</li>
				</div>
			</div>
			<hr>

			<div class="main-contents">

				<!-- エラーメッセージ -->
				<c:if test="${ not empty errorMessages }">
					<div class="errorMessages">
						<ul>
							<c:forEach items="${errorMessages}" var="message">
								<li><c:out value="${message}" />
							</c:forEach>
						</ul>
					</div>
					<c:remove var="errorMessages" scope="session" />
				</c:if>

				<form action="newpost" method="post"><br />
					<table>
						<tr>
							<th>タイトル<br />
								（30文字まで）</th>
							<td><input name="title" size="100" required="true" value="${post.title}"></input></td>
						</tr>
						<tr>
							<th>カテゴリー<br />
								（10文字まで）</th>
							<td><input name="category" id="category" required="true" value="${post.category}"/></td>
						</tr>
						<tr>
							<th>本文<br />
							（1000文字まで）</th>
							<td><textarea name="text" cols="100" rows="10" required="true">${post.text}</textarea></td>
						</tr>
					</table>
					<button type="submit" class="btn btn-primary">新規投稿</button>
				</form>

			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>