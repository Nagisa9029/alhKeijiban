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
					<label for="account">ログインID</label>
						<input name="account" value="${editUser.account}" id="account" required="true" /> <br />
					<label for="name">名前</label>
						<input name="name" value="${editUser.name}" id="name" required="true" /> <br />
					<label for="password">パスワード</label>
						<input name="password" value="${editUser.password}" id="password" /> <br />
					<label for="branch_id">支店</label>
						<select name="branch_id" required="true">
							<option value="${editUser.branchId}">${editUser.branchName}</option>
							<option value="1">本社</option>
							<option value="2">支店A</option>
							<option value="3">支店B</option>
							<option value="4">支店C</option>
							<option value="5">支店D</option>
						</select><br />
					<label for="position_id">部署・役職</label>
						<select name="position_id" required="true">
							<option value="${editUser.positionId}">${editUser.positionName}</option>
							<option value="1">総務人事</option>
							<option value="2">情報管理</option>
							<option value="3">店長</option>
							<option value="4">社員</option>
						</select><br />

					<input type="hidden" name="id" value="${editUser.id}" id="id" />
					<input type="hidden" name="is_stopped" value="${editUser.isStopped}" id="is_stopped" />
					<input type="submit" value="登録" /> <br />
					<a href="./">戻る</a>
				</form>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>