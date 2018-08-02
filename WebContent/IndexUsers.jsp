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
		<script type="text/javascript" src="./js/app.js"></script>
		<title>ユーザー一覧</title>
	</head>

	<c:import url="header.jsp" />

	<body>
		<div class="container">
			<h1 style="margin-top: 30px;">ユーザー一覧</h1>
			<hr>
			<a href="./">戻る</a>

			<div class="main-contents">
				<div class="header">
					<a href="newuser">ユーザー新規登録</a>
					<a href="logout">ログアウト</a>
				</div>

				<div class="Users">

					<table class="table table-hover table-condensed">
						<thead>
							<tr>
								<th>名前</th>
								<th>アカウント</th>
								<th>支店</th>
								<th>部署・役職</th>
								<th></th>
								<th>アカウント状況</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<!--<c:out value="${user.id}" />-->
									<td><a href="edit?id=${user.id}">
										<c:out value="${user.name}" /></a>
									</td>
									<td><c:out value="${user.account}" /></td>
									<!--<c:out value="${user.password}" />-->
									<td><c:out value="${user.branchName}" /></td>
									<td><c:out value="${user.positionName}" /></td>
									<td><c:out value="${user.createdDate}" /></td>
									<td>
										<form action="edit" method="post" onSubmit="return check()"><br />
											<input type="hidden" name="id" value="${user.id}" />
											<input type="hidden" name="account" value="${user.account}" />
											<input type="hidden" name="name" value="${user.name}" id="name" />
											<input type="hidden" name="password" value="" />
											<input type="hidden" name="branch_id" value="${user.branchId}" />
											<input type="hidden" name="position_id" value="${user.positionId}" />
											<c:if test="${ user.isStopped == 1 }">
												<input type="hidden" name="is_stopped" value="0" />
												<button type="submit" class="btn btn-success">停止</button></c:if>
											<c:if test="${ user.isStopped == 0 }">
												<input type="hidden" name="is_stopped" value="1" />
												<button type="submit" class="btn btn-default">復活</button></c:if>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>

	<c:import url="footer.jsp" />

</html>
