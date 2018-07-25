<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ユーザー一覧</title>
	</head>
	<body>
		<div class="main-contents">
			<div class="header">
				<a href="./">戻る</a>
				<a href="newuser">ユーザー新規登録</a>
				<a href="logout">ログアウト</a>
			</div>

			<div class="Users">

				<table>
					<tr>
						<th></th>
						<th>名前</th>
						<th>アカウント</th>
						<th>支店</th>
						<th>部署・役職</th>
						<th>is_stopped</th>
						<td>リンク</td>
					</tr>
					<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><a href="edit?id=${user.id}">
							<c:out value="${user.name}" /></a>
						</td>
						<td><c:out value="${user.account}" /></td>
						<!--<c:out value="${user.password}" />-->
						<td><c:out value="${user.branchName}" /></td>
						<td><c:out value="${user.positionName}" /></td>
						<td>
							<form action="edit" method="post"><br />
								<c:if test="${ user.is_stopped == 1 }">
									<input type="hidden" name="is_stopped" value="0" />
									<input type="submit" value="停止" /></c:if>
								<c:if test="${ user.is_stopped == 0 }">
									<input type="hidden" name="is_stopped" value="1" />
									<input type="submit" value="復活" /></c:if>
							</form>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="copyright"> Copyright(c)Takuto Nakamura</div>
		</div>
	</body>
</html>
