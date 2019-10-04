<%--


--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>

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
            <form name="currencyRateForm" action="${root}/option/saveCurrencyRate" method="post">
                <div class="row" style="margin: 0">
                    <legend>Add Currency Rate</legend>
                </div>
                <input type="hidden" name="userId" value="${user.userId}"/>
                <div class="row">
                    <div class="col-md-12">

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Date</div>
                            <div class="col-md-6">
                                <div class='input-group date' style="width: 100%">
                                    <%--<input type='text' required class="datePicker" style="height: 100%;"--%>
                                           <%--name="date" "onchange="loadRate(this)/>--%>
                                    <%--<span class="input-group-addon">--%>
                                        <%--<span style="font-size:18px;color:#3e71fb" class="fa fa-calendar"></span>--%>
                                    <%--</span>--%>
                                    <div class='input-group date'>
                                        <input type='text' style="height:35px" class="form-control" required="required" value="" name="date" onchange="loadRate(this)"/>
                                        <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                                    </div>
                                </div>

                            </div>

                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Currency From</div>
                            <div class="col-md-6">
                                <select class="chosen" id="currencyFrom" required name="currencyFrom"
                                        style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${currencyTypes}" var="currencyType">
                                        <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Currency To</div>
                            <div class="col-md-6">
                                <select class="chosen" id="currencyTo" required name="currencyTo" style="width: 100%">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${currencyTypes}" var="currencyType">
                                        <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px">Exchange Rate</div>
                            <div class="col-md-6">
                                <input type="text" id="exchangeRateId" onkeyup="isNumber(this)" required="required"
                                       name="exchangeRate"
                                       maxlength="16"
                                       style="width: 100%"/>
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row" style="margin-top: 8px">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">.
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top: 3px"
                                           href="${root}/option/currencyRate">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary"
                                                style="width: 100%; font-size: 14px">
                                            Save
                                        </button>
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
        <table id="dataTable" class="display compact" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>Currency From</th>
                <th>Currency To</th>
                <th>Date</th>
                <th>Rate</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="currencyRate" items="${currencyRates}">
                <tr id="${currencyRate.id}">
                    <c:forEach items="${currencyTypes}" var="currencyTypeFrom">
                        <c:if test="${currencyTypeFrom.currencyId == currencyRate.currencyFrom}">
                            <td>${currencyTypeFrom.currencyCode}</td>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${currencyTypes}" var="currencyTypeTo">
                        <c:if test="${currencyTypeTo.currencyId == currencyRate.currencyTo}">
                            <td>${currencyTypeTo.currencyCode}</td>
                        </c:if>
                    </c:forEach>
                    <td>${currencyRate.date}</td>
                    <td>${currencyRate.exchangeRate}</td>
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
                    window.location.href = "./currencyRate";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Currency Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./currencyRate";
                })
            }
        }
    });

    function loadRate(date) {
        var table = $('#dataTable').DataTable();
        table.clear().draw();
        $("#dataTable").find("tr:gt(0)").remove();
        var dd = formatDate(date.value);

        $.ajax({
            url: '${root}/option/getRateByDate/' + dd,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var markup = "<tr>" +
                        "<td>" + data[i][0] + "</td>" +
                        "<td>" + data[i][1] + "</td>" +
                        "<td>" + data[i][2] + "</td>" +
                        "<td>" + data[i][3] + "</td>" +
                        "</tr>";
                    $("table tbody").append(markup);
                }
            }
        });
    }

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });



</script>