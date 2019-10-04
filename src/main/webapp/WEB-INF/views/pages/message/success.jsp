<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${root}/resources/css/sweetalert2.css">
<link rel="stylesheet" href="${root}/resources/css/sweetalert2.min.css">

<script src="${root}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${root}/resources/js/sweetalert2.common.js"></script>
<script src="${root}/resources/js/sweetalert2.js"></script>
<script src="${root}/resources/js/sweetalert2.min.js"></script>


<script>
	$(function() {
		swal({
			title : 'Auto close alert!',
			text : 'I will close in 2 seconds.',
			timer : 2000
		}).then(function() {
		},
		// handling the promise rejection
		function(dismiss) {
			if (dismiss === 'timer') {
				console.log('I was closed by the timer')
			}
		})

	});
</script>