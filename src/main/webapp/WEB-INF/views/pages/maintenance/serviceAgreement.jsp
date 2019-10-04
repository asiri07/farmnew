<%--
  Created by IntelliJ IDEA.
  User: Hashane
  Date: 2019-01-07
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
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
<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Service Agreement</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="serviceAgreementForm" action="${root}/maintenance/saveServiceAgreement" method="post">


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Type</div>
                            <div class="col-md-8">
                                <select style="width: 100%" name="category" id="category" onchange="loadAssetByType()">
                                    <option value="0">---SELECT---</option>
                                    <option value="Plant">Plant</option>
                                    <option value="Office">Office Equipment</option>
                                    <option value="Lab">Lab Equipment</option>
                                    <option value="Teaching">Teaching Equipment</option>
                                    <option value="Sports">Sports Equipment</option>
                                    <option value="Software">Software</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">

                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">
                                <select required id="assetId" class="chosen" style="width: 100%"
                                        onchange="clearFields(),loadTransactionNoList()" name="assetId">
                                    <option value="">---SELECT---</option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-7">
                                <select id="transactionSelectId" class="chosen" style="width: 100%"
                                        onchange="loadServiceAgreementDetails()"
                                        name="transactionSelect">
                                    <option value="0">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">

                            <div class="col-md-4" style="padding-top: 10px">Agreement No.</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="agreeNumberId" maxlength="10"
                                       name="agreeNumber"/>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Company</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="companyId" maxlength="100"
                                       name="agreeCompany"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Agreement Period (Month)</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="agreePeriodId" maxlength="10"
                                       onblur="loadToDate()" onkeypress="return isNumberOnly(event)"
                                       name="agreePeriod"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">

                            <div class="col-md-5" style="padding-top: 10px">Currency Type</div>
                            <div class="col-md-7">
                                <select id="currencyTypeId" required class="select_style"
                                        name="currencyType" style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <c:forEach items="${currencyTypes}" var="currencyType">
                                        <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                    </c:forEach>
                                </select>
                            </div>


                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Period From</div>
                            <div class="col-md-8">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control"
                                           onchange="checkFromDate(),loadToDate()" style="height: 93%"
                                           name="agreeFrom" id="agreeFromId"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Value of Service Agreement</div>
                            <div class="col-md-7">
                                <input type="text" name="costDisplay" id="agreeCostId" maxlength="14" required
                                       required onkeypress="return isNumberAndDecimalOnly(event)"
                                       onblur="return addComma(id)"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Period To</div>
                            <div class="col-md-8">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control" onchange="checkToDate()"
                                           name="agreeTo" id="agreeToId" style="height: 93%"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">


                            <div class="col-md-5" style="padding-top: 10px">Interval of the Service</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="agreeServiceIntervalId"
                                       maxlength="10"
                                       name="agreeServiceInterval"/>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-6">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-5"></div>
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top: 3px"
                                           href="${root}/maintenance/serviceAgreement">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary"
                                                style="width: 100%; font-size: 14px" id="btnSave">
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <input type="hidden" id="agreeId" name="agreeId" value="0"/>
                <input type="hidden" id="transactionNo" name="transactionNo" value="${transactionNo}"/>

            </form>
        </div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>


<script type="text/javascript">


    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Saved Successfully!!! Transaction No : ";
        } else {
            message = "Updated Successfully!!! ";
        }

        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message + transactionNo,
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./serviceAgreement";
                })

            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./serviceAgreement";
                })
            }
        }
    });

    function loadTransactionNoList() {

        var assetId = $("#assetId").find("option:selected").val();
        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadServiceAgreeNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i].agreeId + "'>" + data[i].transactionNo
                        + "</option>");
                }
            }
        });
    }

    function loadServiceAgreementDetails() {
        var serviceAgreementId = $("#transactionSelectId").find("option:selected").val();
        $.ajax({
            url: '${root}/maintenance/loadServiceAgreementDetailsByServiceAgreeId/' + serviceAgreementId,
            success: function (data) {
                if (data != "") {
                    $("input[name=agreeId]").val(data.agreeId);
                    $("input[name=transactionNo]").val(data.transactionNo);
                    $("input[name=agreeNumber]").val(data.agreeNumber);
                    $("input[name=agreePeriod]").val(data.agreePeriod);
                    $("input[name=agreeFrom]").val(dateFormators(data.agreeFrom));
                    $("input[name=agreeTo]").val(dateFormators(data.agreeTo));
                    $("input[name=costDisplay]").val(data.agreeCost);
                    $("input[name=agreeCompany]").val(data.agreeCompany);
                    $("input[name=agreeServiceInterval]").val(data.agreeServiceInterval);
                    document.getElementById("currencyTypeId").value = data.currencyType;
                    addComma("agreeCostId");
                    $("#btnSave").html('Update');
                }
            }
        });
    }

    function clearFields() {
        $("input[name=transactionNo]").val("");
        $("input[name=agreeId]").val("0");
        $("input[name=agreeNumber]").val("");
        $("input[name=agreePeriod]").val("");
        $("input[name=agreeFrom]").val("");
        $("input[name=agreeTo]").val("");
        $("input[name=costDisplay]").val("");
        $("input[name=agreeCompany]").val("");
        $("input[name=agreeServiceInterval]").val("");
        document.getElementById("currencyTypeId").selectedIndex = 0;
        $("#btnSave").html('Save');
    }

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


    function loadToDate() {
        var periodFrom = $("input[name=agreeFrom]").val();
        var period = $("input[name=agreePeriod]").val();

        var date = formatDate(periodFrom);

        if (period != "") {
            if (periodFrom != "") {
                $.ajax({
                    url: '${root}/maintenance/loadToDate',
                    data: {date: date, period: period},
                    success: function (data) {
                        var date2 = data;
                        $("input[name=agreeTo]").val(dateFormators(date2));
                    }
                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Enter Service Agree Period",
                type: 'warning',
                timer: 1000
            });
            $("input[name=agreeTo]").val("");
        }
    }

    function checkFromDate() {

        var periodFrom = $("input[name=agreeFrom]").val();
        var periodTo = $("input[name=agreeTo]").val();
        var assetId = $("#assetId").find("option:selected").val();

        if (assetId > 0) {
            if (periodFrom != "") {
                var date = formatDate(periodFrom);
                $.ajax({
                    url: '${root}/maintenance/checkDeedSignedDate',
                    data: {date: date, assetId: assetId},
                    success: function (data) {
                        if (data > 0) {
                            var msg;
                            switch (data) {
                                case 1:
                                    msg = "";
                                    break;
                                case 2:
                                    msg = "Please select date, after Asset registered date";
                                    break;
                            }
                            swal({
                                title: 'Warning',
                                text: msg,
                                type: 'warning',
                                timer: 1000
                            })
                            $("input[name=agreeFrom]").val("");
                        }

                    }

                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=agreeFrom]").val("");
        }
    }

    function checkToDate() {

        var periodFrom = $("input[name=agreeFrom]").val();
        var periodTo = $("input[name=agreeTo]").val();
        var assetId = $("#assetId").find("option:selected").val();

        if (assetId > 0) {
            if (periodFrom == "") {
                swal({
                    title: 'Warning',
                    text: "Select From Date",
                    type: 'warning',
                    timer: 1000
                })
                $("input[name=agreeTo]").val("");
            }
        }
        else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=agreeTo]").val("");
        }
    }

    function loadAssetByType() {
        var type = $('#category').val(); // get selected value
        var userBranch = '<%= session.getAttribute("userBranch") %>';
        $.ajax({
            url: '${root}/maintenance/loadAssetByTypeServiceAgree/' + type + '/'+ userBranch ,
            success: function (data) {
                if (data != null) {
                    $("#assetId").empty();
                    $("#assetId").append('<option value="">---SELECT---</option>');
                    for (var i = 0; i < data.length; i++) {
                        $("#assetId").append(
                            "<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2]
                            + "</option>");
                    }
                }
            }
        });
    }

</script>
