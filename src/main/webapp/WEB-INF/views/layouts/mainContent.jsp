<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 10/8/2017
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fix Asset Register</title>

    <!-- CSS File -->
    <link href="${root}/resources/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href="${root}/resources/css/mainLayout.css">
    <link rel="stylesheet" href="${root}/resources/css/style.css">
    <link href="${root}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${root}/resources/css/bs_leftnavi.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${root}/resources/css/sweetalert2.css">
    <link rel="stylesheet" href="${root}/resources/css/sweetalert2.min.css">
    <link rel="stylesheet" href="${root}/resources/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="${root}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${root}/resources/bootstrap/css/bootstrap-datepicker.css">


    <!-- JS FILE -->
    <script src="${root}/resources/js/jquery-2.1.1.min.js"></script>
    <script src="${root}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${root}/resources/js/bs_leftnavi.js" type="text/javascript"></script>
    <script src="${root}/resources/js/sweetalert2.common.js"></script>
    <script src="${root}/resources/js/sweetalert2.js"></script>
    <script src="${root}/resources/js/sweetalert2.min.js"></script>
    <script src="${root}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${root}/resources/bootstrap/js/bootstrap-datepicker.js"></script>



</head>
<body>
    <div class="content" id="body">
        <tiles:insertAttribute name="body"/>
    </div>
</section>



</body>
</html>