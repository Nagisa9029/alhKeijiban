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
		<title>ユーザー編集</title>
	</head>

	<header>

	<c:import url="header.jsp" />

	<div class="header-bottom">
		<nav style="position: absolute;">
			<ul class="nav-list">
				<a href="./"><img src="./fonts/home.png" width="15"> HOME　</a>
				<img src="./fonts/right.png" width="10"><a href="users">　管理画面　</a>
				<img src="./fonts/right.png" width="10">　ユーザー編集
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
			<a href="./users">戻る</a>
			<div class="row">
				<div class="col-xs-3">
					<h1 style="margin-top: 30px;">ユーザー情報編集</h1>
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

				<form action="edit" method="post"><br />
					<table class="table">
						<tr>
							<th>ログインID</th>
							<td><input name="account" value="${editUser.account}" id="account" required="true" size="50" /> (半角英数)<br />
								※6文字以上、20文字以下で設定してください</td>
						</tr>
						<tr>
							<th>名前</th>
							<td><input name="name" value="${editUser.name}" id="name" required="true" size="50" /><br />
								※10文字以下で設定してください</td>
						</tr>
						<tr>
							<th>パスワード</th>
							<td><input type="password" name="password" value="" id="password" size="50" />(記号を含む半角英数)<br />
								※6文字以上20文字以下で設定してください</td>
						</tr>
						<tr>
							<th>パスワード（確認用）</th>
							<td><input type="password" name="passwordTest" value="" id="passwordTest" size="50" /></td>
						</tr>
						<tr>
							<th>支店</th>
							<td><select name="branch_id" required="true">
								<c:choose>
									<c:when test="${loginUser.id == editUser.id }">
										<c:forEach items="${branches}" var="branch">
											<c:if test="${editUser.branchId == branch.id }">
												<option value="${branch.id}" selected><c:out value="${branch.name}" /></option>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach items="${branches}" var="branch">
											<c:if test="${editUser.branchId == branch.id }">
												<option value="${branch.id}" selected><c:out value="${branch.name}" /></option>
											</c:if>
											<c:if test="${editUser.branchId != branch.id }">
												<option value="${branch.id}"><c:out value="${branch.name}" /></option>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select></td>
						</tr>
						<tr>
							<th>部署・役職</th>
							<td><select name="position_id" required="true">
								<c:choose>
									<c:when test="${loginUser.id == editUser.id }">
										<c:forEach items="${positions}" var="position">
											<c:if test="${editUser.positionId == position.id }">
												<option value="${position.id}" selected><c:out value="${position.name}" /></option>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach items="${positions}" var="position">
											<c:if test="${editUser.positionId == position.id }">
												<option value="${position.id}" selected><c:out value="${position.name}" /></option>
											</c:if>
											<c:if test="${editUser.positionId != position.id }">
												<option value="${position.id}"><c:out value="${position.name}" /></option>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select></td>
						</tr>
					</table>

					<input type="hidden" name="accountTest" value="${editUser.account}" />
					<input type="hidden" name="id" value="${editUser.id}" id="id" />
					<input type="hidden" name="is_stopped" value="${editUser.isStopped}" id="is_stopped" />
					<button type="submit" class="btn btn-primary">編集</button>
				</form>


			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>