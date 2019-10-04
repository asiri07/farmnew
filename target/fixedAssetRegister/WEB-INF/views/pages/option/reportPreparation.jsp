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

        Modification Date     : 7/1/2019:
        User                  : Dilusha Kumari
        Purpose               : Modified Asset Code loading method

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

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="row">
    <div class="col-md-7">
        <div class="page">
            <div class="row" style="margin: 0">
                <legend>Report Preparation</legend>
            </div>
            <c:if test="${pageType == 1 && status == 'true'}">
                <div class="row" style="margin: 0">
                    <h3 style="color: #2a62bc;text-align: center">Successfully !! Preparation Data : ${assetCount}</h3>
                </div>
            </c:if>
            <form id="assetFormID"
                  action="${root}/preparation/doPreparation" method="POST">
                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px">Date From</div>
                    <div class="col-md-6">
                        <div class='input-group date'>
                            <input type='text' class="form-control" id="fromDate" required="required" value="" style="height: 35px"
                                   name="fromDate"/>
                            <span class="input-group-addon">
                                <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                        </div>
                    </div>
                    <%--<div class="col-md-5">--%>

                        <%--<input type="text" autocomplete="off" required class="datePicker"--%>
                               <%--id="datePicker"--%>
                               <%--style="width:110%; height: 35px"--%>
                               <%--name="fromDate"/>--%>

                    <%--</div>--%>
                    <%--<div class="col-md-1">--%>
                        <%--<span style="text-align:center;font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>--%>
                    <%--</div>--%>

                    <div class="col-md-3"></div>
                </div>
                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px">Date To</div>
                    <div class="col-md-6">
                        <div class='input-group date'>
                            <input type='text' class="form-control" id="toDate" required="required" value="" style="height: 35px"
                                   name="toDate"/>
                            <span class="input-group-addon">
                                <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                                <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                                </span>
                        </div>
                    </div>
                    <%--<div class="col-md-5">--%>

                        <%--<input type="text" autocomplete="off" required class="datePicker"--%>
                               <%--style="width:110%; height: 35px"--%>
                               <%--name="toDate"/>--%>

                    <%--</div>--%>
                    <%--<div class="col-md-1">--%>
                        <%--<span style="text-align:center;font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>--%>
                    <%--</div>--%>

                    <div class="col-md-3"></div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="padding-top: 10px">Currency Type</div>
                    <div class="col-md-6"><select id="currencyId" required class="select_style"
                                                  name="currencyType" style="width: 100%">
                        <option value="0">---SELECT---</option>
                        <c:forEach items="${currencyTypes}" var="currencyType">
                            <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="col-md-3"></div>
                </div>


                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-4">
                                <button type="button" class="btn btn-primary" style="width: 100%; font-size: 14px">Close</button>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary" id="doPreparation" style="width: 100%; font-size: 14px">
                                    Ok
                                </button>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-5"></div>
</div>

<%--<div class="row">--%>
<%--<div class="col-md-12">--%>
<%--<table id="dataTable" class="display compact" width="100%" cellspacing="0">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>Asset Code</th>--%>
<%--<th>Main cat</th>--%>
<%--<th>Sub cat</th>--%>
<%--<th>Detail Cat</th>--%>
<%--<th>Company Code</th>--%>
<%--<th>Department Code</th>--%>
<%--<th>Section Code</th>--%>
<%--<th>Location Code</th>--%>
<%--<th>Life Time</th>--%>
<%--<th>Depreciation Rate</th>--%>
<%--<th>Asset Value</th>--%>
<%--<th>Value After Imp</th>--%>
<%--<th>From Date Value</th>--%>
<%--<th>From DISP Value</th>--%>
<%--<th>To Value</th>--%>
<%--<th>To DSP Value</th>--%>
<%--<th>Current Value</th>--%>
<%--<th>Tax Rate</th>--%>
<%--<th>From Tax Value</th>--%>
<%--<th>From Tax DISP Value</th>--%>
<%--<th>To Tax Disp Value</th>--%>
<%--<th>To Tax Value</th>--%>
<%--<th>Tax Current Value</th>--%>
<%--</tr>--%>
<%--</thead>--%>

<%--<tbody>--%>
<%--<c:forEach var="preparation" items="${preparation}">--%>
<%--<tr>--%>
<%--<td>${preparation.assetCode}</td>--%>
<%--<td>${preparation.mainCat}</td>--%>
<%--<td>${preparation.subCat}</td>--%>
<%--<td>${preparation.detailCat}</td>--%>
<%--<td>${preparation.companyCode}</td>--%>
<%--<td>${preparation.departmentCode}</td>--%>
<%--<td>${preparation.sectionCode}</td>--%>
<%--<td>${preparation.locationCode}</td>--%>
<%--<td>${preparation.lifeTime}</td>--%>
<%--<td>${preparation.depreciationRate} %</td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.assetValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.valueAfterImprovement}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.fromDateValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.fromDateDispValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.toDateValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.toDateDispValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.currentValue}" /></td>--%>
<%--<td>${preparation.taxRate} %</td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.fromDateTaxValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.fromDateTaxDispValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.toDateTaxDispValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.toDateTaxValue}" /></td>--%>
<%--<td><fmt:formatNumber type = "number"--%>
<%--maxFractionDigits = "3" value = "${preparation.taxCurrntValue}" /></td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--</div>--%>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="massage" value="${massage}"/>


<script type="text/javascript">
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $(function () {
        $(".datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
        }));

    });

    $('#doPreparation').click(function () {
        $(".se-pre-con").show();
        $.blockUI({
            message: "<h3>Processing, Please Wait...</h3>",
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .4,
                color: '#fff'
            }
        });
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var massage = $("#massage").val();
        if (pageType == 1) {
            if (status == 'true') {
                //window.location.href = "./reportPreparation";
                swal({
                    title: massage,
                    type: 'success'
                }).then(function () {
                    window.location.href = "./reportPreparation";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: massage,
                    type: 'error',
                    timer: 7000
                }).then(function () {
                    window.location.href = "./reportPreparation";
                })
            }
        }
    });

    function doPreparation() {
        var fromDate = $("#fromDate").val();
        var toDate = $("#toDate").val();
        if (fromDate != null && toDate != null) {
            $.ajax({
                url: '${root}/preparation/doPreparation',
                    data: {fromDate: fromDate, toDate: toDate},
                    success: function (data) {
                    if (data === true) {
                        swal({
                            title: 'success!',
                            type: 'success',
                            timer: 2000
                        }).then(function () {
                            window.location.href = "./reportPreparation";
                        })
                    } else {
                        swal({
                            title: 'Error !',
                            text: 'Report Preparation process Not Completed !!',
                            type: 'error',
                            timer: 7000
                        }).then(function () {
                            window.location.href = "./reportPreparation";
                        })
                    }
                }
            });
        }
    }
</script>