<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	</head>
	<body>
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
					<input name="id" value="${editUser.id}" id="id" type="hidden"/>
				<label for="account">ログインID</label>
					<input name="account" value="${editUser.account}" id="account" /> <br />
				<label for="name">名前</label>
					<input name="name" value="${editUser.name}" id="name" /> <br />
				<label for="password">パスワード</label>
					<input name="password" value="${editUser.password}" id="password" /> <br />
				<label for="branch_id">支店</label>
					<select name="branch_id">
						<option value="1">本社</option>
						<option value="2">支店A</option>
						<option value="3">支店B</option>
						<option value="4">支店C</option>
						<option value="5">支店D</option>
					</select><br />
				<label for="position_id">部署・役職</label>
					<select name="position_id">
						<option value="1">総務人事</option>
						<option value="2">情報管理</option>
						<option value="3">店長</option>
						<option value="4">社員</option>
					</select><br />

				<input type="hidden" name="is_stopped" value="${editUser.is_stopped}" id="is_stopped" />
				<input type="submit" value="登録" /> <br />
				<a href="./">戻る</a>
			</form>
			<div class="copyright">Copyright(c)Takuto Nakamura</div>
		</div>
	</body>
</html>