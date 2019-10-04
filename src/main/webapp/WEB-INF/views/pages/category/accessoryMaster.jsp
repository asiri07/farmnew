<%--
  ~
  ~      Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
  ~       *  PROPRIETARY AND COPYRIGHT NOTICE.
  ~
  ~          This software product contains information which is proprietary to
  ~          and considered a trade secret MsSoftIT Solution .
  ~          It is expressly agreed that it shall not be reproduced in whole or part,
  ~          disclosed, divulged or otherwise made available to any third party directly
  ~          or indirectly.  Reproduction of this product for any purpose is prohibited
  ~          without written authorisation from the The MsSoftIT Solution
  ~          All Rights Reserved.
  ~
  ~          E-Mail mssoftit@gmail.com
  ~          URL : mssoftit.lk
  ~          Created By : Dilusha Kumari
  ~
  --%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<div class="se-pre-con"></div>
<div class="row">
    <div class="col-md-6">
        <div class="page">


            <form name="accessoryMasterForm" action="${root}/category/saveAccessoryMaster" method="POST">
                <div class="row" style="margin: 0">
                    <legend>Accessory Master</legend>
                </div>
                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px"> Code</div>
                    <div class="col-md-9">
                        <input type="text" required name="accerCode" id="accerCode" class="uppercase"
                               onblur="checkAccessoryCode(this)" value="${accessory.accerCode}"
                               id="accerCodeId" maxlength="3" class="form-control" style="width: 86%;height: 30px">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px"> Name</div>
                    <div class="col-md-9">
                        <input type="text" required name="accerName" value="${accessory.accerName}" id="accerNameId"
                               maxlength="50" class="form-control" style="width: 86%; height: 30px;">
                    </div>
                </div>


                <div class="row" style="padding-top: 10px">
                    <div class="col-md-3" style="margin-left: 0px"></div>

                    <div class="col-md-4">
                        <a class="btn btn-primary"
                           style="width: 140px; margin-top:3px;height: 30px; font-size: 14px; margin-left: 0px"
                           href="${root}/category/accessoryMaster">New</a>
                    </div>
                    <div class="col-md-4">

                        <c:choose>
                            <c:when test="${accessory.accerId > 0 }">
                                <button type="submit" class="btn btn-primary btnSubmit"
                                        style="width: 95%; margin-top:3px;height: 30px; font-size: 14px; ">
                                    Update
                                </button>
                                <input type="hidden" name="accerId" value="${accessory.accerId}">
                            </c:when>
                            <c:otherwise>
                                <button type="submit" id="btnSubmit" class="btn btn-primary"
                                        style="width:95%; margin-top:3px;height: 30px; font-size: 14px; margin-right: 100px">
                                    Save
                                </button>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="col-md-1"></div>

                </div>

                <input type="hidden" id="pageType" value="${pageType}"/>
                <input type="hidden" id="status" value="${status}"/>
                <input type="hidden" id="isEdit" value="${isEdit}"/>
            </form>
        </div></div>

        <div class="col-md-6">
            <table id="dataTable" class="display compact" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Accessory Code</th>
                    <th>Accessory Name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="accessoryDetails" items="${accessoryDetails}">
                    <tr id="${accessoryDetails.accerId}">
                        <td>${accessoryDetails.accerCode}</td>
                        <td>${accessoryDetails.accerName}</td>
                        <td><a href="${root}/category/editAccessory/${accessoryDetails.accerId}"><i
                                class="fa fa-pencil-square-o"></i></a></td>
                        <td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--href="${root}/category/editMainCategory/${mainCategory.mcatId}"--%>
        </div>
    </div>


    <script type="text/javascript">


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
            var isEdit = $("#isEdit").val();
            if (isEdit == 1) {
                $('#accerCode').prop('readonly', true);
            }
            if (pageType == 1) {
                if (status == 'true') {
                    swal({
                        title: 'Success!',
                        type: 'success',
                        timer: 1000
                    }).then(function () {
                        window.location.href = "./accessoryMaster";
                    })
                } else {
                    swal({
                        title: 'Error !',
                        text: 'Accessory Added Fail !!',
                        type: 'error',
                        timer: 1000
                    }).then(function () {
                        window.location.href = "./accessoryMaster";
                    })
                }
            }

        });

        $(window).load(function () {
            // Animate loader off screen
            $(".se-pre-con").fadeOut("slow");
        });

        $(function () {
            $('.date').datepicker({
                format: 'yyyy/mm/dd',
                autoclose: true
            });
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
                    url: '${root}/category/deleteAccessoryMaster/' + trId,
                    success: function (data) {
                        if (data == 1) {
                            swal({
                                title: 'Deleted!',
                                text: 'Your file has been deleted.',
                                type: 'success',
                                time: 1000
                            })
                            var row = id.parentNode.parentNode;
                            row.parentNode.removeChild(row);
                            window.location.href = "./accessoryMaster";
                        } else if (data == 2) {
                            swal({
                                title: 'Sorry!',
                                text: 'Accessory already used !!',
                                type: 'warning',
                                timer: 1000
                            })
                        } else {
                            swal({
                                title: 'Sorry!',
                                text: 'Can\'t Delete Accesory!!',
                                type: 'warning',
                                timer: 1000
                            })
                        }
                    }
                });
            })
        }


        function checkAccessoryCode(code) {
            var accerCode = code.value;
            var isEdit = $("#isEdit").val();
            if (isEdit != 1) {
                $.ajax({
                    url: '${root}/category/checkAccessory/' + accerCode,
                    success: function (data) {
                        if (!data) {
                            // $("#accerCode").focus();
                            $("#accerCode").addClass("txtError");
                            $("#accerCode").val("");
                            swal({
                                title: 'Warning',
                                text: 'Duplicate Accessory code !! Try again...!',
                                type: 'warning',
                                timer: 1000
                            })
                        } else {
                            $("#accerCode").removeClass("txtError");
                        }
                    },
                    error: function () {
                    }
                });
            }
        }
    </script>
