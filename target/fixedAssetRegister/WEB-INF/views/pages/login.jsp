<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="resources/bootstrap/4.1.3/css/bootstrap.min.css">

<script src="resources/js/jquery-2.1.1.min.js"></script>
<script src="resources/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="resources/js/login.js"></script>
</head>
<body>

	<div class="container">
		<div class="login-container">
			<div id="output"></div>
			<div class="avatar"></div>
			<div class="form-box">
				<form action="${root}/login" name="loginForm" method="post">
					<input name="username" type="text" placeholder="userName">
					<input type="password" name="password" placeholder="Password">
					<button class="btn btn-info btn-block login" type="submit">Login</button>
					<input type="hidden" name="${_csrf.parameterName}"
						   value="${_csrf.token}" />
				</form>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="msg">${message}</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>