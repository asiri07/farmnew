<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%--<%@ taglib uri="http://www.kendoui.com/jsp/tags" prefix="kendo" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fixed Asset Register with Asset Maintenance</title>

    <!-- CSS File -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>



    <link href="${root}/resources/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>

    <link rel="stylesheet" href="${root}/resources/css/mainLayout.css">

    <link href="${root}/resources/css/multiSelect.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${root}/resources/css/style.css">
    <link href="${root}/resources/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">

    <link href="${root}/resources/css/bs_leftnavi.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${root}/resources/css/sweetalert2.css">
    <link rel="stylesheet" href="${root}/resources/css/sweetalert2.min.css">
    <link rel="stylesheet" href="${root}/resources/css/dataTables.bootstrap.min.css">
    <link href="${root}/resources/bootstrap/4.1.3/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link rel="stylesheet" href="${root}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${root}/resources/bootstrap/4.1.3/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${root}/resources/css/choosen.css">
    <link href="${root}/resources/css/table.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${root}/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" href="${root}/resources/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="${root}/resources/css/jquery-ui.theme.css"/>
    <link rel="stylesheet" href="${root}/resources/css/jquery-ui.theme.min.css"/>


    <!-- JS FILE -->
    <script src="${root}/resources/js/jquery-2.1.1.min.js"></script>
    <script src="${root}/resources/js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${root}/resources/js/jquery.multi-select.js"></script>

    <script src="${root}/resources/js/jquery-ui.js"></script>
    <script src="${root}}/resources/js/jquery-ui.min.js"></script>

    <script src="${root}/resources/bootstrap/4.1.3/js/bootstrap.min.js"></script>
     <script src="${root}/resources/js/jquery.dataTables.min.js"></script>

    <script src="${root}/resources/js/bs_leftnavi.js" type="text/javascript"></script>
    <script src="${root}/resources/js/sweetalert2.all.min.js"></script>

    <script src="${root}/resources/bootstrap/4.1.3/js/bootstrap-datepicker.js"></script>

    <script src="${root}/resources/js/choosen.js"></script>
    <script src="${root}/resources/js/jquery.multi-select.js"></script>




    <script type="text/javascript" src="${root}/resources/js/jquery.blockUI.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<section class="col-md-12 header"><tiles:insertAttribute name="header"/></section>
<div style="overflow:auto">
<section class="col-md-12">
    <div class="sidebar">
        <tiles:insertAttribute name="menu"/>
    </div>
    <div class="content" id="body">
        <tiles:insertAttribute name="body"/>
    </div>
</section>
    <footer class="footer col-md-12"><tiles:insertAttribute
            name="footer"/>
    </footer>

</div>


</body>
</html>