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
            <form name="userForm"
                  action="${root}/user/loadUseJobList" method="post">
                <div class="row" style="margin: 0">
                    <legend>User Job Define</legend>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="clearfix"></div>
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

                        <div class="row" id="divUser">
                            <div class="col-md-3" style="padding-top: 10px">User Name</div>
                            <div class="col-md-6">
                                <select id="userId" required name="userId" style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach items="${users}" var="user">
                                        <option value="${user.userId}">${user.username}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px">
                                    Load
                                </button>
                            </div>

                        </div>

                        <div class="row" id="divBranch">
                            <div class="col-md-3">Branch</div>
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
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px">
                                    Load
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
    $('#divBranch').hide();
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
            searching: false,
            paging: false,
            info: false,
            "paging": false
        });
    });

    function checkBranch() {
        if (document.getElementById('branch').checked) {
            $('#divBranch').show();
            $('#divUser').hide();
        }
        else {
            $('#divUser').show();
            $('#divBranch').hide();
            document.getElementById("branchId").selectedIndex = 0;
        }
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                window.location.href = "./userJobs";
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'User Job Assigned Fail !!',
                    type: 'error',
                    timer: 1000
                })
            }
        }

    });

</script>