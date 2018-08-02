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
			<h1 style="text-align: center; margin-top: 30px;"><img src="./fonts/login.png" width="40">Login</h1>
			<hr>

			<div class="main-contents">

				<!-- エラーメッセージ -->
				<c:if test="${ not empty errorMessages }">
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
							<label for="account"><img src="./fonts/account.png" width="20">アカウント名</label><br />
							<input name="account" id="account" size="40" /><br />
						</div>

						<div style="margin: 30px;">
							<label for="password"><img src="./fonts/password.png" width="20">パスワード</label><br />
							<input name="password" type="password" id="password" size="40" /><br />
						</div>
					<button type="submit" class="btn btn-primary">ログイン</button>
					</form>
				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>