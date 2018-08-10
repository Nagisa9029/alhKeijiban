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
		<script type="text/javascript" src="./js/app.js"></script>
		<title>管理画面（ユーザー一覧）</title>
	</head>

	<header>

	<c:import url="header.jsp" />

	<div class="header-bottom">
		<nav style="position: absolute;">
			<ul class="nav-list">
				<a href="./"><img src="./fonts/home.png" width="15"> HOME　</a>
				<img src="./fonts/right.png" width="10">　管理画面
			</ul>
		</nav>
		<nav style="text-align: right;">
			<ul class="nav-list">
				<li class="nav-list-item"><a href="newuser"><img src="./fonts/user.png" width="20"> ユーザー新規登録</a></li>
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
					<h1 style="margin-top: 30px;">管理画面 / ユーザー一覧</h1>
				</div>
				<div class="col-xs-6"></div>
				<div class="col-xs-3" style="position: absolute; right: 150px; top: 230px;">
					<li><label>ユーザー名：</label>${loginUser.name}</li>
				</div>
			</div>
			<br>


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

				<div class="Users">

					<table class="table table-hover table-condensed">
						<thead style="background-color: #CCFFFF;">
							<tr>
								<th>名前</th>
								<th>ログインID</th>
								<th>支店</th>
								<th>部署・役職</th>
								<th>投稿件数</th>
								<th>アカウント状況変更</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<% int postTotal = 0; %>
								<c:forEach items="${posts}" var="post">
									<c:if test="${user.id == post.userId}">
										<% postTotal += 1; %>
									</c:if>
								</c:forEach>
								<% int commentTotal = 0; %>
								<c:forEach items="${comments}" var="comment">
									<c:if test="${user.id == comment.userId}">
										<% commentTotal += 1; %>
									</c:if>
								</c:forEach>

								<tr>
									<td><a href="edit?id=${user.id}">
										<c:out value="${user.name}" /></a>
									</td>
									<td><c:out value="${user.account}" /></td>
									<td><c:out value="${user.branchName}" /></td>
									<td><c:out value="${user.positionName}" /></td>
									<td>投　稿　数：<%= postTotal %> 件<br />
										コメント数：<%= commentTotal %> 件
									</td>
									<td>
										<c:choose>
											<c:when test="${ user.id == loginUser.id }">
												<button class="btn btn-default disabled">ログイン中</button>
											</c:when>
											<c:when test="${ user.isStopped == 1 }">
												<form action="edit" method="post" onSubmit="return check()"><br />
													<input type="hidden" name="id" value="${user.id}" />
													<input type="hidden" name="account" value="${user.account}" />
													<input type="hidden" name="name" value="${user.name}" id="name" />
													<input type="hidden" name="password" value="" />
													<input type="hidden" name="branch_id" value="${user.branchId}" />
													<input type="hidden" name="position_id" value="${user.positionId}" />
													<input type="hidden" name="accountTest" value="${user.account}" />
													<input type="hidden" name="is_stopped" value="0" />
													<button type="submit" class="btn btn-danger">停止</button>
												</form>
											</c:when>
											<c:when test="${ user.isStopped == 0 }">
												<form action="edit" method="post" onSubmit="return check()"><br />
													<input type="hidden" name="id" value="${user.id}" />
													<input type="hidden" name="account" value="${user.account}" />
													<input type="hidden" name="name" value="${user.name}" id="name" />
													<input type="hidden" name="password" value="" />
													<input type="hidden" name="branch_id" value="${user.branchId}" />
													<input type="hidden" name="position_id" value="${user.positionId}" />
													<input type="hidden" name="accountTest" value="${user.account}" />
													<input type="hidden" name="is_stopped" value="1" />
													<button type="submit" class="btn btn-success">復活</button>
												</form>
											</c:when>


										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>
