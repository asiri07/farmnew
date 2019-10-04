<%--
  Created by IntelliJ IDEA.
  User: Hashane 
  Date: 2019-08-09
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form name="checkHigherUserForm" id="checkHigherUserForm" method="get">
    <div class="modal fade" id="myModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Higher User Credential</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <p>Username</p>
                    <input type="text" id="usernameModal" name="username">
                    <br>
                    <p>Password</p>
                    <input type="password" id="passwordModal" name="password">
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" style="font-size: 14px"
                            onclick="checkHigherUser(this)">Login
                    </button>
                    <button type="button" class="btn btn-primary" style="font-size: 14px"
                            data-dismiss="modal">Close
                    </button>
                </div>

            </div>
        </div>
    </div>
</form>
