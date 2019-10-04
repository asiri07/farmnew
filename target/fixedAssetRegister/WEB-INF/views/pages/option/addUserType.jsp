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
            <form name="userType" action="${root}/user/saveUserType" method="post">
                <div class="row" style="margin: 0">
                    <legend>Add User Type</legend>
                </div>
                <input type="hidden" name="userTypeId" value="${userType.userTypeId}"/>
                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px">User Type</div>
                    <div class="col-md-6">
                        <input type="text" id="userType" value="${userType.userType}" required="required"
                               name="userType" style="width: 100%"/>
                    </div>
                    <div class="col-md-3"></div>
                </div>

                <div class="row" style="margin-top: 15px">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">

                        <div class="row">
                            <div class="col-md-6" style="padding-top: 5px">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top:-3px" href="${root}/user/userType">New</a>
                            </div>
                            <div class="col-md-6">
                                <c:choose>
                                    <c:when test="${userType.userTypeId > 0 }">
                                        <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px">Update
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px">Save</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </form>
        </div>
    </div>

    <div class="col-md-5">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>User Type</th>
                <th>Edit</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="userType" items="${userTypes}">
                <tr id="${userType.userTypeId}">
                    <td>${userType.userType}</td>
                    <td><a href="${root}/user/editUserType/${userType.userTypeId}"><i class="fa fa-pencil-square-o"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
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
                    window.location.href = "./userType";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'User Type Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./userType";
                })
            }
        }

    });

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
                url: '${root}/allocation/deleteCompany/' + trId,
                success: function (data) {
                    if (data == 1) {
                        swal({
                            title: 'Deleted!',
                            text: 'Your file has been deleted.',
                            type: 'success',
                            timer: 1000
                        })
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Company Code already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title:'Sorry!',
                            text:'Can\'t Delete Company Code !!',
                            type:'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }
</script>