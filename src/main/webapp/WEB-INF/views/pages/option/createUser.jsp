<%--
     Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
     *  PROPRIETARY AND COPYRIGHT NOTICE.

        This software product contains information which is proprietary to
        and considered a trade secret MsSoftIT Solution .
        It is expressly agreed that it shall not be reproduced in whole or part,
        disclosed, divulged or otherwise made available to any third party directly
        or indirectly.  Reproduction of this product for any purpose is prohibited
        without written authorisation from the The MsSoftIT Solution
        All Rights Reserved.

        E-Mail mssoftit@gmail.com
        URL : mssoftit.lk
        Created By : Mahendra Sri Dayarathna

--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<style>
    .no-js #loader {
        display: none;
    }

    .js #loader {
        display: block;
        position: absolute;
        left: 100px;
        top: 0;
    }

    .se-pre-con {
        position: fixed;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
        z-index: 9999;
        background: url(${root}/resources/images/Preloader_3.gif) center no-repeat #fff;
        opacity: .8;
    }

</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-7">
        <div class="page">
            <form name="userForm" action="${root}/user/saveUser" method="post">
                <div class="row" style="margin: 0">
                    <legend>Add New User</legend>
                </div>
                <%--value="${user.userId}"--%>
                <input type="hidden" name="userId" value="0"/>
                <input type="hidden" id="individualId" name="individual" value="1"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <input type="radio" name="usert" id="individual"
                                       onclick="checkBranch()" checked>Individual<br>
                            </div>
                            <div class="col-md-3">

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <input type="radio" name="usert" id="branch" value="Branch" onclick="checkBranch()">Branch<br>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row" id="divBranch">
                            <div class="col-md-3" style="padding-top: 10px">Branch</div>
                            <div class="col-md-6">
                                <select id="branchId" name="depId" style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach var="department" items="${department}">
                                        <option selected="selected" required
                                                value="${department.depId}">${department.depCode}
                                            - ${department.depDes}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">User Name</div>
                            <div class="col-md-6">
                                <%--value="${user.username}"--%>
                                <input type="text" id="userNameId" onblur="checkUserName(this)"
                                       required="required" name="username" style="width: 100%"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-3">User Level</div>
                            <div class="col-md-6">
                                <select id="userTypeId" required name="userType.userTypeId" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${userTypes}" var="userType">
                                        <option value="${userType.userTypeId}">${userType.userType}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Password</div>
                            <div class="col-md-6">
                                <input type="password" id="passwordId" required="required" name="password"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>


                        <div class="row" style="margin-top: 15px">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">

                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top: 3px"
                                           href="${root}/user/newUser">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <%--<c:choose>--%>
                                        <%--<c:when test="${user.userId>0}">--%>
                                        <%--<button type="submit" class="btn btn-primary"--%>
                                        <%--style="width: 100%; font-size: 14px ">--%>
                                        <%--Update--%>
                                        <%--</button>--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                        <button onclick="checkBranchSelect()" class="btn btn-primary"
                                                style="width: 100%; font-size: 14px">Save
                                        </button>
                                        <%--</c:otherwise>--%>
                                        <%--</c:choose>--%>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3"></div>

                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5">
        <%--<table id="dataTable" class="display compact" width="100%" cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
        <%--<th>User Name</th>--%>
        <%--<th>User Level</th>--%>
        <%--<th>Edit</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>

        <%--<tbody>--%>
        <%--<c:forEach var="user" items="${users}">--%>
        <%--<tr id="${user.userId}">--%>
        <%--<td>${user.username}</td>--%>
        <%--<td>${user.userType.userType}</td>--%>
        <%--<td><a href="${root}/user/editUser/${user.userId}"><i class="fa fa-pencil-square-o"></i></a></td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
        <%--</table>--%>

    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>

<script type="text/javascript">
    $('#divBranch').hide();
    $("#userNameId").val("");
    $("#passwordId").val("");
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");

    });

    $('.btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    function checkBranchSelect() {
        var branch = $("#branchId").val();
        if (document.getElementById('branch').checked) {
            if (branch == 0) {
                $("#branchId").addClass("txtError");
                $("#branchId").val("");
                swal({
                    title: 'Warning',
                    text: 'Please Select Branch !',
                    type: 'warning',
                    timer: 1000
                })
            } else {
                $("#branchId").removeClass("txtError");
            }
        }

    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./newUser";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'User Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./newUser";
                })
            }
        }

    });

    function checkBranch() {
        if (document.getElementById('branch').checked) {
            $('#divBranch').show();
            $('#individualId').val("0");
        }
        else {
            $('#divBranch').hide();
            $('#individualId').val("1");
            document.getElementById("branchId").selectedIndex = 0;
        }
    }

    function checkUserName(code) {
        var userCode = code.value;
        var isEdit = $("#isEdit").val();
        if (isEdit != 1) {
            $.ajax({
                url: '${root}/user/checkUserName/' + userCode,
                success: function (data) {
                    if (!data) {
                        // $("#userNameId").focus();
                        $("#userNameId").addClass("txtError");
                        $("#userNameId").val("");
                        swal({
                            title: 'Warning',
                            text: 'Duplicate  User !! Try again...!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        $("#userNameId").removeClass("txtError");
                    }
                },
                error: function () {

                }
            });
        }
    }

    function onDelete(id) {
        var trId = $(id).closest('tr').attr('id');
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(function () {
            $.ajax({
                url: '${root}/location/deleteCity/' + trId,
                success: function (data) {
                    if (data == 1) {
                        swal(
                            'Deleted!',
                            'Your file has been deleted.',
                            'success'
                        )
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                    } else if (data == 2) {
                        swal(
                            'Sorry!',
                            'City Code already used !!',
                            'warning'
                        )
                    } else {
                        swal(
                            'Sorry!',
                            'Can\'t Delete City Code !!',
                            'warning'
                        )
                    }
                }
            });
        })
    }

</script>