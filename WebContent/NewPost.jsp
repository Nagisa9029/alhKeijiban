<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿</title>
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
			<form action="newpost" method="post"><br />
				<label for="title">タイトル</label>（30文字まで）
					<textarea name="title" cols="100" rows="1"></textarea><br />
				<label for="text">本文</label>（1000文字まで）
					<textarea name="text" cols="100" rows="10"></textarea><br />
				<label for="category">カテゴリー</label><br />
					<input name="category" id="category" /> <br />

				<input type="submit" value="登録" /> <br />
				<a href="./">戻る</a>
			</form>
			<div class="copyright">Copyright(c)Takuto Nakamura</div>
		</div>
	</body>
</html>