
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#title {
	background-color: #751717;
	color: #fff; font-family : "bgMed", arial, helvetica, sans-serif;
	font-size : 30px; height : 9%;
	margin-left: 3%;
	font-family: "bgMed", arial, helvetica, sans-serif;
	font-size: 30px;
	height:1px;
}

</style>
</head>
<body>
<div class="row">
	<div class="col-md-10 comName">
		<a href="${root}/" style="color: #ffffff">${companyName}</a>
	</div>
<div class="col-md-2 rightMenu">
	<a href="${root}/logout" class="btn btn-info btn-lg">
		<span class="glyphicon glyphicon-log-out"></span> Log out
	</a>
</div>
</div>

</body>
</html>