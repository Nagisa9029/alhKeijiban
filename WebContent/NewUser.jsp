<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/style.css" rel="stylesheet" type="text/css">
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="./js/jQuery.min.js"></script>
		<title>ユーザー登録</title>
	</head>

	<header>

	<c:import url="header.jsp" />

	<div class="header-bottom">
		<nav style="position: absolute;">
			<ul class="nav-list">
				<a href="./"><img src="./fonts/home.png" width="15"> HOME　</a>
				<img src="./fonts/right.png" width="10"><a href="users">　管理画面　</a>
				<img src="./fonts/right.png" width="10">　新規ユーザー登録
			</ul>
		</nav>
		<nav style="text-align: right;">
			<ul class="nav-list">
				<li class="nav-list-item"><a href="logout"><img src="./fonts/logout.png" width="20"> Logout</a></li>
			</ul>
		</nav>
	</div>
	</header>

	<body>
	<div class="top-box"></div>
		<div class="container">
			<h1 style="margin-top: 30px;">新規ユーザー登録</h1>
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

				<form action="newuser" method="post"><br />
					<table class="table">
						<tr>
							<th>ログインID</th>
							<td><input name="account" id="account" required="true" size="50" value="${user.account}" />（半角英数）<br />
								※6文字以上、20文字以下で設定してください</td>
						</tr>
						<tr>
							<th>名前</th>
							<td><input name="name" id="name" required="true" size="50" value="${user.name}" /><br />
								※10文字以下で設定してください</td>
						</tr>
						<tr>
							<th>パスワード</th>
							<td><input type="password" name="password" id="password" required="true" size="50" />（記号を含む半角英数）<br />
								※6文字以上20文字以下で設定してください</td>
						</tr>
						<tr>
							<th>パスワード（確認用）</th>
							<td><input type="password" name="passwordTest" id="passwordTest" required="true" size="50" /></td>
						</tr>
						<tr>
							<th>支店</th>
							<td><select name="branch_id" required="true">
									<option></option>
									<c:forEach items="${branches}" var="branch">
										<c:if test="${user.branchId == branch.id}">
											<option value="${branch.id}" selected><c:out value="${branch.name}" /></option>
										</c:if>
										<c:if test="${user.branchId != branch.id}">
											<option value="${branch.id}"><c:out value="${branch.name}" /></option>
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>部署・役職</th>
							<td><select name="position_id" required="true">
									<option></option>
									<c:forEach items="${positions}" var="position">
										<c:if test="${user.positionId == position.id}">
											<option value="${position.id}" selected><c:out value="${position.name}" /></option>
										</c:if>
										<c:if test="${user.positionId != position.id}">
											<option value="${position.id}"><c:out value="${position.name}" /></option>
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
					</table>
					<input type="hidden" name="is_stopped" value="1" />
					<button type="submit" class="btn btn-primary">登録</button>
				</form>

			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>