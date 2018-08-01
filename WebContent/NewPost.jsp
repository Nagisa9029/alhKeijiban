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
		<title>新規投稿</title>
	</head>

	<c:import url="header.jsp" />

	<body>
		<div class="container">
			<h1>新規投稿</h1>
			<hr>
			<a href="./">戻る</a>

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

				<form action="newpost" method="post"><br />
					<table>
						<tr>
							<th>タイトル<br />
								（30文字まで）</th>
							<td><textarea name="title" cols="100" rows="1" required="true"></textarea></td>
						</tr>
						<tr>
							<th>カテゴリー<br />
								（10文字まで）</th>
							<td><input name="category" id="category" /></td>
						</tr>
						<tr>
							<th>本文<br />
							（1000文字まで）</th>
							<td><textarea name="text" cols="100" rows="10" required="true"></textarea></td>
						</tr>
					</table>
					<button type="submit" class="btn btn-primary">新規投稿</button>
				</form>

			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>