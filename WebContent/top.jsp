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
				<div class="col-xs-3" style="position: absolute; right: 150px; top: 210px;">
					<li><label>所属部署：</label>${loginUser.branchName }　${loginUser.positionName}</li>
					<li><label>ユーザー名：</label>${loginUser.name}</li>
				</div>
			</div>
			<hr>

			<div class="main-contents">
				<div class="row">
					<div>
						<form action="./" method="get">
							<label for="cate">日付での絞り込み：</label>
							<input type="date" name="dateStr" value="${dateStr}" /> ～ <input type="date" name="dateEnd" value="${dateEnd}" /><br />
							<label for="cate">カテゴリー検索　：</label>
							<input name="cate" id="cate" size="38" value="${cate}" /> <br />
							<label for="cate">支店での絞り込み：</label>
							<select name="bran">
								<option></option>
								<c:forEach items="${branches}" var="branch">
									<c:if test="${bran == branch.name}">
										<option value="${branch.name}" selected><c:out value="${branch.name}" /></option>
									</c:if>
									<c:if test="${bran != branch.name}">
										<option value="${branch.name}"><c:out value="${branch.name}" /></option>
									</c:if>
								</c:forEach>
							</select><br />
							<input type="submit" value="　検索　">
						</form>
					</div>


					<% int h1 = 0; %><% int h2 = 0; %><% int h3 = 0; %><% int h4 = 0; %><% int h5 = 0; %>
					<c:forEach items="${popos}" var="popo">
						<c:if test="${popo.branchName == '本社'}"><% h1 = h1 + 1; %></c:if>
						<c:if test="${popo.branchName == '支店A'}"><% h2 = h2 + 1; %></c:if>
						<c:if test="${popo.branchName == '支店B'}"><% h3 = h3 + 1; %></c:if>
						<c:if test="${popo.branchName == '支店C'}"><% h4 = h4 + 1; %></c:if>
						<c:if test="${popo.branchName == '支店D'}"><% h5 = h5 + 1; %></c:if>
					</c:forEach>

					<div style="position: absolute; left: 1000px;">
						<ul id="normal" class="dropmenu">
							<li><a href="#">支店別投稿数</a>
								<ul>
									<li><a href="./?bran=本社">本社  <span class="badg"><%= h1 %></span> 件</a></li>
									<li><a href="./?bran=支店A">支店A<span class="badg"><%= h2 %></span> 件</a></li>
									<li><a href="./?bran=支店B">支店B<span class="badg"><%= h3 %></span> 件</a></li>
									<li><a href="./?bran=支店C">支店C<span class="badg"><%= h4 %></span> 件</a></li>
									<li><a href="./?bran=支店D">支店D<span class="badg"><%= h5 %></span> 件</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
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
							<c:choose>
								<c:when test="${loginUser.id == post.userId}">
									<form action="delete" method="post" onSubmit="return destroy()">
										<input type="hidden" name="id" value="${post.id}" />
										<input type="submit" value="投稿削除">
									</form>
								</c:when>
								<c:when test="${loginUser.positionId == 2}">
									<form action="delete" method="post" onSubmit="return destroy()">
										<input type="hidden" name="id" value="${post.id}" />
										<button type="submit" class="btn btn-warning">情報管理権限による削除</button>
									</form>
								</c:when>
								<c:when test="${loginUser.branchId != 1 && loginUser.branchName == post.branchName}">
									<form action="delete" method="post" onSubmit="return destroy()">
										<input type="hidden" name="id" value="${post.id}" />
										<button type="submit" class="btn btn-warning">店長権限による削除</button>
									</form>
								</c:when>
							</c:choose>

							<!--<c:if test="${loginUser.id == post.userId || loginUser.branchName == post.branchName || loginUser.positionId == 2}">
								<form action="delete" method="post" onSubmit="return destroy()">
									<input type="hidden" name="id" value="${post.id}" />
									<input type="submit" value="投稿削除" >
								</form>
							</c:if>-->
							<div class="box-title">
								<span class="date"><fmt:formatDate value="${post.createdDate}" pattern="yyyy/MM/dd" /></span>
							</div>
							<div>投稿者：<span>${post.branchName} ${post.positionName} / </span><span class="name"><c:out value="${post.name}" /></span></div>
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
												<c:choose>
													<c:when test="${loginUser.id == comment.userId}">
														<form action="DeleteComment" method="post" onSubmit="return destroy()">
															<input type="hidden" name="id" value="${comment.id}" />
															<input type="submit" value="コメント削除">
														</form>
													</c:when>
													<c:when test="${loginUser.positionId == 2}">
														<form action="DeleteComment" method="post" onSubmit="return destroy()">
															<input type="hidden" name="id" value="${comment.id}" />
															<button type="submit" class="btn btn-warning">情報管理権限による削除</button>
														</form>
													</c:when>
													<c:when test="${loginUser.branchId != 1 && loginUser.branchName == comment.branchName}">
														<form action="DeleteComment" method="post" onSubmit="return destroy()">
															<input type="hidden" name="id" value="${comment.id}" />
															<button type="submit" class="btn btn-warning">店長権限による削除</button>
														</form>
													</c:when>
												</c:choose>

												<!--<c:if test="${loginUser.id == comment.userId || loginUser.branchName == comment.branchName}">
													<form action="DeleteComment" method="post" onSubmit="return destroy()">
														<input type="hidden" name="id" value="${comment.id}" />
														<input type="submit" value="コメント削除">
													</form>
												</c:if>-->
												<div>投稿者：<span>${comment.branchName} ${comment.positionName} / </span><span class="name"><c:out value="${comment.name}" /></span></div>
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
									<textarea name="text" cols="129" rows="5" class="tweet-box" required="true"><c:if test="${post.id == comment.postId}">${comment.text}</c:if></textarea><br />
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