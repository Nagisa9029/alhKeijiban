<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./css/style.css" rel="stylesheet" type="text/css">
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<link href="./css/bootstrap-theme.min.css">
		<link href="./fonts/glyphicons-halflings-regular.eot">
		<link href="./fonts/glyphicons-halflings-regular.woff">
		<link href="./fonts/glyphicons-halflings-regular.woff2">
		<script type="text/javascript" src="./js/jQuery.min.js"></script>
		<title>ログイン</title>
	</head>

	<c:import url="header.jsp" />

	<body>
		<div class="container">
			<h1 style="text-align: center; margin-top: 30px;">Login</h1>
			<hr>

			<div class="main-contents">
				<c:if test="${ not empty errorMessage }">
					<div class="errorMessages">
						<ul>
							<c:forEach items="${errorMessages }" var="message">
								<li><c:out value="${message }" />
							</c:forEach>
						</ul>
					</div>
					<c:remove var="errorMessages" scope="session" />
				</c:if>

				<div class="login-box">
					<form action="login" method="post"><br />
						<div style="margin: 30px;">
							<label for="account">アカウント名</label><br />
							<input name="account" id="account" size="50" /><br />
						</div>

						<div style="margin: 30px;">
							<label for="password">パスワード</label><br />
							<input name="password" type="password" id="password" size="50" /><br />
						</div>
					<button type="submit" class="btn btn-primary">ログイン</button>
					</form>
				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>