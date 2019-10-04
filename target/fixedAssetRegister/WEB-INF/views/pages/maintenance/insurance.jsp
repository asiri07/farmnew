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
        <legend>Insurance</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="insuranceForm"
                  action="${root}/maintenance/saveInsurance" method="post">

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Type</div>
                            <div class="col-md-8">
                                <select style="width: 100%" name="category" id="category" onchange="loadAssetByType()">
                                    <option value="0">---SELECT---</option>
                                    <option value="Land">Land</option>
                                    <option value="Building">Building</option>
                                    <option value="Vehicle">Vehicle</option>
                                    <option value="Computer">Computer</option>
                                    <option value="Plant">Plant</option>
                                    <option value="Furniture">Furniture</option>
                                    <option value="Office">Office Equipment</option>
                                    <option value="Lab">Lab Equipment</option>
                                    <option value="Teaching">Teaching Equipment</option>
                                    <option value="Fixtures">Fixtures And Fitting</option>
                                    <option value="Sports">Sports Equipment</option>
                                </select>
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
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">
                                <select required id="assetId" class="chosen" style="width: 100%"
                                        onchange="clearFields(),loadTransactionNoList()"
                                        name="assetId">
                                    <option value="">---SELECT---</option>
                                    <%--<c:forEach var="asset" items="${assets}">--%>
                                    <%--<option value="${asset.asId}">${asset.asCode} - ${asset.asDes}</option>--%>
                                    <%--</c:forEach>--%>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Insured Value</div>
                            <div class="col-md-7">
                                <input type="text" name="insuredValueDisplay" id="insuranceValueId" maxlength="14"
                                       onblur="return addComma(id)"
                                       required onkeypress="return isNumberAndDecimalOnly(event)"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-8">
                                <select id="transactionSelectId" class="chosen" style="width: 100%"
                                        onchange="loadInsuranceDetails()"
                                        name="transactionSelect">
                                    <option value="0">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Premium</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="premiumId"
                                       onblur="return addComma(id)" onkeypress="return isNumberAndDecimalOnly(event)"
                                       maxlength="100"
                                       name="premiumDisplay"/>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Insurance Policy</div>
                            <div class="col-md-8">
                                <input type="text" name="insurancePolicy" id="insurancePolicyId" maxlength="100"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Insuranced Company</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="insuranceCompanyId"
                                       maxlength="100"
                                       name="insuranceCompany"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Insurance Period (Month)</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="insurancePeriodId"
                                       onkeypress="return isNumberOnly(event)"
                                       maxlength="50" onchange="loadToDate()"
                                       name="insurancePeriod"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Agent</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="insuranceAgentId" maxlength="50"
                                       name="insuranceAgent"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row" style="margin-top: 10px">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Period From</div>
                            <div class="col-md-8">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control"
                                           name="insurancePeriodFrom" id="insurancePeriodFromId" style="height:92%;"
                                           onchange="checkFromDate(),loadToDate()"/>
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
                            <div class="col-md-5" style="padding-top: 10px">Telephone No.</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="insuranceTelephoneNoId"
                                       maxlength="15"
                                       name="insuranceTelephoneNo"/>
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
                                    <input type='text' required class="form-control"
                                           name="insurancePeriodTo" style="height:92%; " onchange="checkToDate()"/>
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
                            <div class="col-md-5" style="padding-top: 10px">Address</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="insuranceAddressId"
                                       maxlength="100"
                                       name="insuranceAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Policy No.</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="insurancePolicyNoId"
                                       maxlength="10"
                                       name="insurancePolicyNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">


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
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                           href="${root}/maintenance/insurance">New</a>
                                    </div>
                                    <div class="col-md-6" style="margin-top:-3px">
                                        <button type="submit" class="btn btn-primary"
                                                style="width: 100%; font-size: 14px" id="btnSave">Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>
                <input type="hidden" name="insuranceID" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"
                       style="width: 100%"/>
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
                    timer: 1000,
                }).then(function () {
                    window.location.href = "./insurance";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./insurance";
                })
            }
        }
    });


    function loadTransactionNoList() {

        var assetId = $("#assetId").find("option:selected").val();

        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadInsuranceNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i].insuranceID + "'>" + data[i].transactionNo
                        + "</option>");
                }
            }
        });
    }

    function loadInsuranceDetails() {
        var insuranceId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/maintenance/loadInsuranceDetailsByInsuranceId/' + insuranceId,
            success: function (data) {
                if (data != "") {
                    $("input[name=insuranceID]").val(data.insuranceID);
                    $("input[name=transactionNo]").val(data.transactionNo);
                    $("input[name=insurancePolicyNo]").val(data.insurancePolicyNo);
                    $("input[name=insurancePolicy]").val(data.insurancePolicy);
                    $("input[name=insurancePeriodFrom]").val(dateFormators(data.insurancePeriodFrom));
                    $("input[name=insurancePeriodTo]").val(dateFormators(data.insurancePeriodTo));
                    $("input[name=insurancePeriod]").val(data.insurancePeriod)
                    $("input[name=insuredValueDisplay]").val(data.insuredValueDisplay);
                    $("input[name=premiumDisplay]").val(data.premiumDisplay);
                    $("input[name=insuranceCompany]").val(data.insuranceCompany);
                    $("input[name=insuranceAgent]").val(data.insuranceAgent);
                    $("input[name=insuranceAddress]").val(data.insuranceAddress);
                    $("input[name=insuranceTelephoneNo]").val(data.insuranceTelephoneNo);
                    document.getElementById('category').value = data.category;
                    document.getElementById('currencyTypeId').value = data.currencyType;
                    $("#btnSave").html('Update');
                }
            }
        });
    }


    function clearFields() {
        $("input[name=insuranceID]").val("0");
        $("input[name=transactionNo]").val("");
        $("input[name=insurancePolicyNo]").val("");
        $("input[name=insurancePolicy]").val("");
        $("input[name=insurancePeriodFrom]").val("");
        $("input[name=insurancePeriodTo]").val("");
        $("input[name=insurancePeriod]").val("");
        $("input[name=insuredValueDisplay]").val("");
        $("input[name=premium]").val("");
        $("input[name=insuranceCompany]").val("");
        $("input[name=insuranceAgent]").val("");
        $("input[name=insuranceAddress]").val("");
        $("input[name=insuranceTelephoneNo]").val("");
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

    function checkFromDate() {

        var periodFrom = $("input[name=insurancePeriodFrom]").val();
        var periodTo = $("input[name=insurancePeriodTo]").val();
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
                            $("input[name=insurancePeriodFrom]").val("");
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
            $("input[name=insurancePeriodFrom]").val("");
        }
    }

    function checkToDate() {
        var periodFrom = $("input[name=insurancePeriodFrom]").val();
        var periodTo = $("input[name=insurancePeriodTo]").val();
        var assetId = $("#assetId").find("option:selected").val();

        if (assetId > 0) {
            if (periodFrom == "") {
                swal({
                    title: 'Warning',
                    text: "Select From Date",
                    type: 'warning',
                    timer: 1000
                })
                $("input[name=insurancePeriodTo]").val("");
            }
        }

        else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=insurancePeriodTo]").val("");
        }
    }


    function loadToDate() {
        var periodFrom = $("input[name=insurancePeriodFrom]").val();
        var period = $("input[name=insurancePeriod]").val();

        var date = formatDate(periodFrom);


        if (period != "") {
            if (periodFrom != "") {
                $.ajax({
                    url: '${root}/maintenance/loadToDate',
                    data: {date: date, period: period},

                    success: function (data) {
                        var date2 = data;
                        $("input[name=insurancePeriodTo]").val(dateFormators(date2));
                    }
                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Enter Warranty Agree Period",
                type: 'warning',
                timer: 1000
            });
            $("input[name=insurancePeriodTo]").val("");
        }
    }


    function loadAssetByType() {
        var type = $('#category').val(); // get selected value
        var userBranch = '<%= session.getAttribute("userBranch") %>';
        $.ajax({
            url: '${root}/maintenance/loadAssetByTypeInsurance/' + type + '/' + userBranch,
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
