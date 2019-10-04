<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
            <form name="incomeTaxForm"
                  action="${root}/data/saveIncomeTax" method="post">

                <div class="row" style="margin: 0">
                    <legend>Income Tax</legend>
                </div>
                <input type="hidden" name="incId" value="${incomTax.incId}" />
                <div class="row">

                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-6">
                                <select class="chosen" id="assetCodeId" name="asset.asId" style="width: 95%">
                                    <option>---SELECT---</option>
                                    <c:forEach var="asset" items="${assets}">
                                        <c:if test="${asset.asId == incomTax.asset.asId}">
                                            <option selected value="${asset.asId}">${asset.asCode}</option>
                                        </c:if>
                                        <option value="${asset.asId}">${asset.asCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Depreciation Rate</div>
                            <div class="col-md-6">
                                <input type="text" onkeyup="isNumber(this)" value="${incomTax.depRate}" required name="depRate" style="width: 90%"/>
                                %
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Accounting From Year</div>
                            <div class="col-md-6">
                                <div class='input-group date' id="datetimepicker6">
                                    <input type='text' required class="form-control" value="<fmt:formatDate pattern = "yyyy/MM/dd"
         value = "${incomTax.accFromYear}" />" name="accFromYear"/>
                                    <span class="input-group-addon">
                                        <button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Accounting To Year</div>
                            <div class="col-md-6">
                                <div class='input-group date' id="datetimepicker7">
                                    <input type='text' required class="form-control" value="<fmt:formatDate pattern = "yyyy/MM/dd"
         value = "${incomTax.accToYear}" />" name="accToYear"/>
                                    <span class="input-group-addon">
                                        <button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-md-3">
                                        <button type="button" class="btn btn-primary" style="width: 100%">Help</button>
                                    </div>
                                    <div class="col-md-3">
                                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;"
                                           href="${root}/data/incomeTax">New</a>
                                    </div>
                                    <div class="col-md-3">
                                        <c:choose>
                                            <c:when test="${incomTax.incId > 0 }">
                                                <button type="submit" class="btn btn-primary" style="width: 100%">
                                                    Update
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary" style="width: 100%">Save
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-3"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5">
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Asset Code</th>
                <th>From Year</th>
                <th>To Year</th>
                <th>Rate</th>
                <th>Edit</th>
                <th>Delete</th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="incomTax" items="${incomTaxs}">
                <tr id="${incomTax.incId}">
                    <td>${incomTax.asset.asCode}</td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd"
                                        value = "${incomTax.accFromYear}" /></td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd"
                        value = "${incomTax.accToYear}"/></td>
                    <td>${incomTax.depRate}%</td>
                    <td><a href="${root}/data/editIncomTax/${incomTax.incId}"><i class="fa fa-pencil-square-o"></i></a></td>
                    <td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<script type="text/javascript">
    $(".chosen").chosen();
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
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'IncomTax Added Fail !!',
                    type: 'error',
                    timer: 1000
                })
            }
        }

    });

    $(function () {
        $('#datetimepicker6').datepicker();
        $('#datetimepicker7').datepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("changeDate", function (e) {

            $('#datetimepicker7').datepicker('setStartDate',e.date);
        });
//        $("#datetimepicker7").on("changeDate", function (e) {
//            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
//        });
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
                url : '${root}/data/deleteIncomTax/'+trId,
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
                    } else if (data == 2) {
                        swal({
                            title: 'Sorry!',
                            text: 'Incometax already used !!',
                            type: 'warning',
                            timer: 1000
                        })
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Incometax!!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

</script>