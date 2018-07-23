<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>掲示板</title>
	</head>
	<body>
		<div class="main-contents">
			<div class="header">
				<c:if test="${ empty loginUser }">
					<a href="login">ログイン</a>
					<a href="newuser">登録する</a>
				</c:if>
				<c:if test="${ not empty loginUser }">
					<a href="newpost">新規投稿</a>
					<a href="settings">設定</a>
					<a href="logout">ログアウト</a>
				</c:if>
			</div>

			<div class="posts">
				<c:forEach items="${posts}" var="post">
					<div class="post">
						<div class="account-name">
							<span class="date"><fmt:formatDate value="${post.created_date}" pattern="yyyy/MM/dd HH:mm:ss" /></span>
							<span class="name"><c:out value="${post.name}" /></span>
						</div>
						<div class="title"><c:out value="${post.title}" /></div>
						<div class="text"><c:out value="${post.text}" /></div>
						<div class="category"><c:out value="${post.category}" /></div>
					</div>

				</c:forEach>
			</div>
			<div class="copyright"> Copyright(c)Takuto Nakamura</div>
		</div>
	</body>
</html>