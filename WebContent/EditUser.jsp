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

	<c:import url="header.jsp" />

	<body>
		<div class="container">
			<h1>ユーザー情報編集</h1>
			<hr>
			<a href="users">戻る</a>

			<div class="main-contents">
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
							<td><input name="password" value="" id="password" size="50" />(記号を含む半角英数)<br />
								※6文字以上20文字以下で設定してください</td>
						</tr>
						<tr>
							<th>パスワード（確認用）</th>
							<td><input name="passwordTest" value="" id="passwordTest" size="50" /></td>
						</tr>
						<tr>
							<th>支店</th>
							<td><select name="branch_id" required="true">
									<option value="${editUser.branchId}"></option>
									<option></option>
									<c:forEach items="${branches}" var="branch">
										<c:if test="${editUser.branchId == branch.id }">
											<option value="${branch.id}" selected>${branch.name}</option>
										</c:if>
										<c:if test="${editUser.branchId != branch.id }">
											<option value="${branch.id}">${branch.name}</option>
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>部署・役職</th>
							<td><select name="position_id" required="true">
									<option value="${editUser.positionId}"></option>
									<c:forEach items="${positions}" var="position">
										<c:if test="${editUser.positionId == position.id }">
											<option value="${position.id}" selected>${position.name}</option>
										</c:if>
										<c:if test="${editUser.positionId != position.id }">
											<option value="${position.id}">${position.name}</option>
										</c:if>

									</c:forEach>
							</select></td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${editUser.id}" id="id" />
					<input type="hidden" name="is_stopped" value="${editUser.isStopped}" id="is_stopped" />
					<button type="submit" class="btn btn-primary">編集</button>
				</form>


			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>