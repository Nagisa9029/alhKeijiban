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
		<script type="text/javascript" src="./js/app.js"></script>
		<title>掲示板</title>
	</head>

	<header>

	<c:import url="header.jsp" />

	<div class="header-bottom">
		<nav style="position: absolute;">
			<ul class="nav-list">
				<a href="./"><img src="./fonts/home.png" width="15"> HOME</a>
			</ul>
		</nav>
		<nav style="text-align: right;">
			<ul class="nav-list">
				<c:if test="${loginUser.positionId != 1 }">
					<li class="nav-list-item"><a href="newpost"><img src="./fonts/post.png" width="20"> 新規投稿</a></li>
					<li class="nav-list-item"><a href="logout"><img src="./fonts/logout.png" width="20"> logout</a></li>
				</c:if>
				<c:if test="${loginUser.positionId == 1 }">
					<li class="nav-list-item"><a href="newpost"><img src="./fonts/post.png" width="20"> 新規投稿</a></li>
					<li class="nav-list-item"><a href="users"><img src="./fonts/account.png" width="20"> 管理画面</a></li>
					<li class="nav-list-item"><a href="logout"><img src="./fonts/logout.png" width="20"> logout</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
	</header>

	<body>
	<div class="top-box"></div>
		<div class="container">
			<br />
			<div class="row">
				<div class="col-xs-3">
					<h1 style="margin-top: 30px;">掲示板</h1>
				</div>
				<div class="col-xs-6"></div>
				<div class="col-xs-3" style="position: absolute; right: 150px; top: 230px;">
					<li><label>ユーザー名：</label>${loginUser.name}</li>
				</div>
			</div>
			<hr>

			<div class="main-contents">

				<form action="./" method="get">
					<label for="cate">日付での絞り込み：</label>
					<input type="date" name="dateStr" value="${dateStr}" />～<input type="date" name="dateEnd" value="${dateEnd}" /><br />
					<label for="cate">カテゴリー検索　：</label>
					<input name="cate" id="cate" value="${cate}" /> <br />
					<input type="submit" value="　検索　">
				</form>
				<hr>

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
					<hr>
					<hr>
				</c:if>

				<div class="posts">
					<c:forEach items="${posts}" var="post">

						<!-- 投稿記事 -->
						<div class="box-post">
							<c:if test="${loginUser.id == post.userId}">
								<form action="delete" method="post" onSubmit="return destroy()">
									<input type="hidden" name="id" value="${post.id}" />
									<input type="submit" value="投稿削除" >
								</form>
							</c:if>
							<div class="box-title">
								<span class="date"><fmt:formatDate value="${post.createdDate}" pattern="yyyy/MM/dd" /></span>
								<span class="name"><c:out value="${post.name}" /></span>
							</div>
							<div class="title">タイトル：<c:out value="${post.title}" /></div>
							<div class="category">カテゴリー：<c:out value="${post.category}" /></div>
							<br />
							<div class="text"><pre><c:out value="${post.text}" /></pre>
							<hr>
							<hr>


							<% int total = 0; %>
							<c:forEach items="${comments}" var="comment">
								<c:if test="${post.id == comment.postId}">
									<% total = total + 1; %>
								</c:if>
							</c:forEach>

							<!-- コメント一覧 -->
							<div class="grad-wrap">
							<input id="${post.id}" class="grad-trigger" type="checkbox">
							<label class="grad-btn" for="${post.id}">コメント <%= total %> 件</label>


								<div class="grad-item" style="padding: 0 50px 0 50px;">
									<c:forEach items="${comments}" var="comment">
										<c:if test="${post.id == comment.postId}">
											<div class="comment">
												<c:if test="${loginUser.id == comment.userId}">
													<form action="DeleteComment" method="post" onSubmit="return destroy()">
														<input type="hidden" name="id" value="${comment.id}" />
														<input type="submit" value="コメント削除">
													</form>
												</c:if>
												<c:out value="${comment.name}" />
												<pre><c:out value="${comment.text}" /></pre>
											</div>
											<hr>
										</c:if>
									</c:forEach>
								</div>
							</div>

							<!-- コメント投稿 -->
							<div class="form-area">
								<form action="./" method="post" >
									<input type="hidden" name="dateStr" value="${dateStr}" />
									<input type="hidden" name="dateEnd" value="${dateEnd}" />
									<input type="hidden" name="cate" value="${cate}" />
									<textarea name="text" cols="100" rows="5" class="tweet-box" required="true">${comment.text}</textarea><br />
									<input type="hidden" name="post" value="${post.id}" />
									<button type="submit" class="btn btn-primary">コメント投稿</button>（500文字まで）
								</form>
							</div>
						</div></div>
					</c:forEach>

				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>