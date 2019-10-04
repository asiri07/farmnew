<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 11/12/2017
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
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

    <div class="col-md-6">
        <div class="page">

            <form name="reasonForm"
                  action="${root}/category/saveReason" method="post">
                <input type="hidden" name="reasonId" value="${rs.reasonId}">
                <div class="row" style="margin: 0">
                    <legend>Disposal Category</legend>
                </div>

                <div class="row">
                    <div class="col-md-2">Reason</div>
                    <div class="col-md-10">
                        <textarea required name="reason" maxlength="50" style="width: 75%">${rs.reason}</textarea>
                    </div>
                    </div>
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-2" ></div>
                    <div class="col-md-4">
                        <a class="btn btn-primary" style="width: 140px; margin-top:3px;height: 30px; font-size: 14px;"  href="${root}/category/disposalCategory">New</a>
                    </div>
                    <div class="col-md-4">
                        <c:choose>
                            <c:when test="${rs.reasonId > 0 }">
                                <button type="submit" class="btn btn-primary btnSubmit"
                                        style="width: 140px;height: 30px; font-size: 14px; ">
                                    Update
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-primary" style="width: 140px;height: 30px; font-size: 14px;">Save</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
        </form>
        </div>

    </div>

    <div class="col-md-6 col-sm-12">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Reason</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="reason" items="${reasons}">
                <tr id="${reason.reasonId}">
                    <td>${reason.reason}</td>
                    <td><a href="${root}/category/editReason/${reason.reasonId}"><i
                            class="fa fa-pencil-square-o"></i></a></td>
                    <td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>
</div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script>

    $('.tabgroup > div').hide();
    $('.tabgroup > div:first-of-type').show();
    $('.tabs a').click(function (e) {
        e.preventDefault();
        var $this = $(this),
            tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
            others = $this.closest('li').siblings().children('a'),
            target = $this.attr('href');
        others.removeClass('active');
        $this.addClass('active');
        $(tabgroup).children('div').hide();
        $(target).show();

    });

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
                    window.location.href = "./disposalCategory";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./disposalCategory";
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
                url: '${root}/category/deleteReason/' + trId,
                success: function (data) {
                    if(data == 1) {
                        swal({
                            title: 'Deleted!',
                            text: 'Your file has been deleted.',
                            type: 'success',
                            timer: 1000
                        })
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                        window.location.href = "./disposalCategory";
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Reason already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Disposal Reason !!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }
</script>