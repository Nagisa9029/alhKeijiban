<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/style.css" rel="stylesheet" type="text/css">
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="./js/jQuery.min.js"></script>
		<title>掲示板</title>
	</head>

	<c:import url="header.jsp" />

	<body>
		<div class="container">
			<h1 style="margin-top: 30px;">掲示板</h1>
			<hr>

			<div class="main-contents">
				<div class="header">
					<c:if test="${loginUser.positionId != 1 }">
						<a href="newpost">新規投稿</a>
						<a href="logout">ログアウト</a>
					</c:if>
					<c:if test="${loginUser.positionId == 1 }">
						<a href="newpost">新規投稿</a>
						<a href="users">管理画面</a>
						<a href="logout">ログアウト</a>
					</c:if>
				</div>
				<hr>

				<form action="index.jsp" method="get">
					<input type="date" name="dateStr" />～<input type="date" name="dateEnd" /><br />
					<label for="category">カテゴリー</label>
					<input name="category" id="category" /> <br />
					<input type="submit" value="検索">
				</form>
				<hr>
				<hr>

				<c:if test="${ not empty errorMessages }">
					<div class="errorMessages">
						<ul>
							<c:forEach items="${errorMessages}" var="message">
								<li><c:out value="${message}" />
							</c:forEach>
						</ul>
					</div>
					<c:remove var="errorMessages" scope="session" />
					<hr>
					<hr>
				</c:if>

				<div class="posts">
					<c:forEach items="${posts}" var="post">

						<!-- 投稿記事 -->
						<div class="post">
							<div class="account-name">
								<span class="date"><fmt:formatDate value="${post.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" /></span>
								<span class="name"><c:out value="${post.name}" /></span>
								<c:if test="${loginUser.id == post.userId}">
									<form action="delete" method="post">
										<input type="hidden" name="id" value="${post.id}" />
										<input type="submit" value="投稿削除" >
									</form>
								</c:if>
							</div>
							<div class="title">タイトル：<c:out value="${post.title}" /></div>
							<div class="category">カテゴリー：<c:out value="${post.category}" /></div>
							<div class="text">本文：<c:out value="${post.text}" /></div>

						</div>
						<hr>

						<!-- コメント一覧 -->
						<c:forEach items="${comments}" var="comment">
							<c:if test="${post.id == comment.postId}">
								<div class="comment">
									<c:out value="${comment.name}" />
									<c:out value="${comment.text}" />
									<c:if test="${loginUser.id == comment.userId}">
										<form action="DeleteComment" method="post">
											<input type="hidden" name="id" value="${comment.id}" />
											<input type="submit" value="コメント削除">
										</form>
									</c:if>
								</div>
							</c:if>
						</c:forEach>

						<!-- コメント投稿 -->
						<div class="form-area">
							<form action="NewComment" method="post" >
								<textarea name="text" cols="100" rows="5" class="tweet-box" required="true"></textarea><br />
								<input type="hidden" name="post" value="${post.id}" />
								<button type="submit" class="btn btn-primary">コメント投稿</button>（500文字まで）
							</form>
						</div>
						<br />
						<br />
						<br />
						<br />
					</c:forEach>

				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>