<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/28/2017
  Time: 2:25 AM
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
<div class="col-md-12 page">
    <form name="transferForm" id="transferFormID" onsubmit="return(validateForm());"
          action="${root}/data/saveTransfers" method="post">

        <div class="row" style="margin: 0">
            <legend>Asset transfers</legend>
        </div>

        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body border border-primary">
                        <%--<div class="col-md-1"></div>--%>
                        <%--<div class="col-md-10" style="border: 1px solid;">--%>
                        <div class="row">
                            <div class="col-md-1"><input type="radio" checked="checked" style="width: 100%"
                                                         value="1"
                                                         name="type"></div>
                            <div class="col-md-3"><label>Internally</label></div>

                            <div class="col-md-1"><input type="radio" style="width: 100%" value="2" name="type">
                            </div>
                            <div class="col-md-3"><label>From Company to Outside</label></div>

                            <div class="col-md-1"><input type="radio" style="width: 100%" value="3" name="type">
                            </div>
                            <div class="col-md-3"><label>From Outside to Company</label></div>
                        </div>
                    </div>
                    <%--<div class="col-md-1"></div>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">

                <div class="row">
                    <div class="col-md-6">
                        <div class="card border border-white">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Date</div>
                                    <div class="col-md-7">
                                        <div class='input-group date'>
                                            <input type='text' required="required"
                                                   onchange="checkDisposalDate()"
                                                   class="form-control" name="date" style="height:35px"/>
                                            <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card border border-white">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Department(Branch)</div>
                                    <div class="col-md-8" id="divDep">
                                        <select required id="depTempID" name="depTemp"
                                                onchange="loadAssetByDep()"
                                                style="width: 100%">
                                            <option value="">---SELECT---</option>
                                            <c:forEach var="departments" items="${departments}">
                                                <option value="${departments.depId}">${departments.depCode}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                                    <div class="col-md-8" id="divAsset">
                                        <select class="chosen" required
                                                style="width: 100%">
                                            <option value=""> ---SELECT---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h3>From</h3>
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Department</div>
                                    <div class="col-md-8">
                                        <input type="text" required width="100" readonly id="departmentID"
                                               name="fromDep"
                                               style="width: 100%"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Section</div>
                                    <div class="col-md-8">
                                        <input type="text" required width="100" readonly id="sectionID"
                                               name="fromSec"
                                               style="width: 100%"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Location</div>
                                    <div class="col-md-8">
                                        <input type="text" required width="100" readonly id="locationID"
                                               name="fromLoc"
                                               style="width: 100%"/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Employee No.</div>
                                    <div class="col-md-8">
                                        <input type="text" width="100" name="fromEmpNo"
                                               style="width: 100%"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">
                                <h3>To</h3>
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Department</div>
                                    <div class="col-md-8">
                                        <select name="toDepartment" required id="depID"
                                                onchange="loadSections()"
                                                style="width: 100%">
                                            <option value="">---SELECT---</option>
                                            <c:forEach var="departments" items="${departments}">
                                                <option value="${departments.depId}">${departments.depCode}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="toDep" id="depCode">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Section</div>
                                    <div class="col-md-8">
                                        <select name="toSection" required id="secID" onchange="loadLocation()"
                                                style="width: 100%">
                                            <option value="">---SELECT---</option>
                                        </select>
                                        <input type="hidden" name="toSec" id="secCode">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Location</div>
                                    <div class="col-md-8">
                                        <select onchange="checkAssetCode()" required name="toLoc" id="locID"
                                                style="width: 100%">
                                            <option value="">---SELECT---</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Employee No.</div>
                                    <div class="col-md-8">
                                        <input type="text" onkeyup="checkAssetCode()" width="100" name="toEmpNo"
                                               style="width: 100%"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <%--<div class="row">&nbsp</div>--%>
                <%--<div class="row">--%>
                <%--<div class="col-md-5" style="border: 1px solid;padding-bottom: 10px">--%>
                <%--<label>From</label>--%>
                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Department</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<input type="text" required width="100" readonly id="departmentID" name="fromDep"--%>
                <%--style="width: 100%"/>--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Section</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<input type="text" required width="100" readonly id="sectionID" name="fromSec"--%>
                <%--style="width: 100%"/>--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Location</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<input type="text" required width="100" readonly id="locationID" name="fromLoc"--%>
                <%--style="width: 100%"/>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Employee No.</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<input type="text" width="100" name="fromEmpNo"--%>
                <%--style="width: 100%"/>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-2"></div>--%>

                <%--<div class="col-md-5" style="border: 1px solid;padding-bottom: 10px">--%>
                <%--<label>To</label>--%>
                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Department</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<select name="toDepartment" required id="depID" onchange="loadSections()"--%>
                <%--style="width: 100%">--%>
                <%--<option value="">---SELECT---</option>--%>
                <%--<c:forEach var="departments" items="${departments}">--%>
                <%--<option value="${departments.depId}">${departments.depCode}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<input type="hidden" name="toDep" id="depCode">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Section</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<select name="toSection" required id="secID" onchange="loadLocation()"--%>
                <%--style="width: 100%">--%>
                <%--<option value="">---SELECT---</option>--%>
                <%--</select>--%>
                <%--<input type="hidden" name="toSec" id="secCode">--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Location</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<select onchange="checkAssetCode()" required name="toLoc" id="locID"--%>
                <%--style="width: 100%">--%>
                <%--<option value="">---SELECT---</option>--%>
                <%--</select>--%>
                <%--</div>--%>
                <%--</div>--%>

                <%--<div class="row">--%>
                <%--<div class="col-md-4" style="padding-top: 10px">Employee No.</div>--%>
                <%--<div class="col-md-8">--%>
                <%--<input type="text" onkeyup="checkAssetCode()" width="100" name="toEmpNo"--%>
                <%--style="width: 100%"/>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<br>--%>
                <%--<div class="row">&nbsp;</div>--%>
                <%--<br>--%>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card" style="border: 3px">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 30px">Issued To</div>
                                    <div class="col-md-8"><textarea maxlength="100" name="issuedTo"
                                                                    style="width: 100%"></textarea></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="col-md-2"></div>--%>
                    <div class="col-md-6">
                        <div class="card" style="border: 3px">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 30px">Comments</div>
                                    <div class="col-md-8"><textarea name="comments" maxlength="100"
                                                                    style="width: 100%"></textarea></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-3">
                            </div>

                            <div class="col-md-3">
                                <a type="button" class="btn btn-primary" h
                                   style="width: 100%;height: 30px; margin-top:3px;  font-size: 14px"
                                   href="${root}/data/transfers">New
                                </a>
                            </div>

                            <div class="col-md-3">
                                <button type="button" onclick="loadBuklTransfer()" class="btn btn-primary"
                                        style="width: 100%;height: 30px; font-size: 14px">Bulk Transfer
                                </button>
                            </div>
                            <%--<div class="col-md-3">--%>
                            <%--<button type="button" id="btnDepartmentTransfer" class="btn btn-primary"--%>
                            <%--style="width: 100%;height: 30px; font-size: 14px">Volume Transfer--%>
                            <%--</button>--%>
                            <%--</div>--%>
                            <div class="col-md-3">
                                <button type="submit" id="btnSubmit" class="btn btn-primary"
                                        style="width: 100%;height: 30px; font-size: 14px">
                                    Direct Transfer
                                </button>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>

    </form>
    <input type="hidden" name="isDepartmentTrasfer" id="isDepartmentTrasfer"/>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>


<form action="${root}/data/loadBulkTransfer" id="bulkTransferForm" method="get"></form>

<script type="text/javascript">
    $(".chosen").chosen();
    $('select[multiple]').multiselect({
        columns: 5,
        placeholder: 'Select options'
    });


    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });

    });


    //    function bulkTrnsfer() {
    //        var check = document.getElementById("chkBulkTransfer").checked;
    //        if (check) {
    //            $("#divAssets").css("display", "block");
    //        } else {
    //            $("#divAssets").css("display", "none");
    //        }
    //    }


    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });


    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });

    function checkAssetCode() {
        var fromDepId = $("#departmentID").val();
        var fromSecId = $("#sectionID").val();
        var fromLocId = $("#locationID").val();
        var formAssetCode = fromDepId + fromSecId + fromLocId;

        var toDepId = $("#depID").find("option:selected").text();
        var toSecId = $("#secID").find("option:selected").text();
        var toLocId = $("#locID").find("option:selected").text();
        var toAssetCode = toDepId + toSecId + toLocId;

        if (formAssetCode === toAssetCode) {
            swal({
                title: 'Warning',
                text: 'Can not Transfer Asset with in same Location !!',
                type: 'warning',
                timer: 5000
            })
            $("#btnSubmit").prop('disabled', true);
        } else {
            $("#btnSubmit").prop('disabled', false);
        }
    }

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: 'Transfer success!',
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./transfers";
                })
            }
        }

    });

    function getAssetAlocation() {
        var assetId = $("#assetCodeId").find("option:selected").val();
        $("#departmentID").val("");
        $("#sectionID").val("");
        $("#locationID").val("");
        if (assetId > 0) {
            $.ajax({
                url: '${root}/data/getAssetAlocation/' + assetId,
                success: function (data) {
                    if (data != "") {
                        var subCat = data.split("-");
                        $("#departmentID").val(subCat[1].toUpperCase());
                        $("#sectionID").val(subCat[2].toUpperCase());
                        $("#locationID").val(subCat[3].toUpperCase());
                        $("#depID")[0].selectedIndex = 0;
                        $("#secID").empty();
                        $("#locID").empty();

                    }
                }
            });
        }

    }

    //    function loadBulkAsset(asserId) {
    //
    //        var MYdata=[{"Value":"1","ValueText":"name1"}
    //            ,{"Value":"2","ValueText":"name2"}
    //            ,{"Value":"3","ValueText":"name3"}];
    //
    //
    //        $('#bulkAsset').html(function(){
    //            return $.map(MYdata, function(v) {
    //                return "<option id='"+ v.Value +"'>" + v.Value + "-" + v.ValueText +"</option>";
    //            }).join('');
    //        });
    //
    //    }

    function loadBuklTransfer() {
        var assetId = $("#assetCodeId").find("option:selected").val();
        if (assetId > 0) {
            $('<input />').attr('type', 'hidden')
                .attr('name', "assetId")
                .attr('value', assetId)
                .appendTo('#bulkTransferForm');
            $("#bulkTransferForm").submit();
        } else {
            $('#divAsset').addClass("txtError").focus();
            swal({
                title: 'Warning',
                text: 'Please select for main asset !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    function loadVolumeTransfer() {
        $('<input />').attr('type', 'hidden')
            .attr('name', "assetId")
            .attr('value', assetId)
            .appendTo('#bulkTransferForm');
        $("#bulkTransferForm").submit();

    }

    function loadAssetByDep() {
        $("#assetCodeId").empty();
        var departmentId = $("#depTempID").find("option:selected").val();
        $.ajax({
            url: '${root}/data/getAssetByDep/' + departmentId,
            success: function (data) {
                $("#divAsset").html('');
                $("#divAsset").html(' <select class="chosen"  required id="assetCodeId"' +
                    '  name="assertId" onchange="getAssetAlocation(),checkDisposalDate()" style="width: 97%;">');
                $("#assetCodeId").append('<option value="">---SELECT---</option>');
                for (var i = 0; i < data.length; i++) {
                    $("#assetCodeId").append("<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                }
                $("#assetCodeId").append('  </select>');
                $(".chosen").chosen();
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select department !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function loadSections() {
        $("#secID").empty();
        var departmentId = $("#depID").find("option:selected").val();
        var departmentCode = $("#depID").find("option:selected").text();

        $("#depCode").val(departmentCode);
        $.ajax({
            url: '${root}/allocation/getSections/' + departmentId,
            success: function (data) {
                $("#secID").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#secID").append(
                        "<option value = '" + subCat[0] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select department !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }

    function loadLocation() {
        $("#locID").empty();
        var sectionId = $("#secID").find("option:selected").val();
        var sectionCode = $("#secID").find("option:selected").text();
        $("#secCode").val(sectionCode);
        $.ajax({
            url: '${root}/allocation/getLocations/' + sectionId,
            success: function (data) {
                $("#locID").append(
                    "<option value = ''> ---SELECT--- </option>");
                for (var i = 0; i < data.length; i++) {
                    var sub = data[i];
                    var subCat = sub.split("-");
                    $("#locID").append(
                        "<option value = '" + subCat[1] + "'>" + subCat[1]
                        + "</option>");
                }
            },
            error: function () {
                swal({
                    title: 'Warning',
                    text: 'Please select section !!',
                    type: 'warning',
                    timer: 1000
                })
            }
        });
    }


    $("#btnDepartmentTransfer").click(function (event) {
        event.preventDefault();
        if (validateForm()) {
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
            $("#isDepartmentTrasfer").val(1);
            $("#transferFormID").submit();
        }
    });

    function validateForm() {
        var date = document.forms["transferForm"]["date"];
        var depTemp = document.forms["transferForm"]["depTemp"];
        var assertId = document.forms["transferForm"]["assertId"];
        var fromEmpNo = document.forms["transferForm"]["fromEmpNo"];
        var toDepartment = document.forms["transferForm"]["toDepartment"];
        var toSection = document.forms["transferForm"]["toSection"];
        var toLoc = document.forms["transferForm"]["toLoc"];
        var toEmpNo = document.forms["transferForm"]["toEmpNo"];
        var issuedTo = document.forms["transferForm"]["issuedTo"];

        if (date.value == "") {
            date.focus();
            swal({
                title: 'Warning',
                text: 'Please enter date.',
                type: 'warning',
                timer: 1000
            })
            return false;
        }
        if (depTemp.value == "") {
            depTemp.focus();
            swal({
                title: 'Warning',
                text: 'Please select department(Branch)',
                type: 'warning',
                timer: 1000
            })
            return false;
        }
        if (assertId.value == "") {
            assertId.focus();
            swal({
                title: 'Warning',
                text: 'Please select asset code.',
                type: 'warning',
                timer: 1000
            })
            return false;
        }

        if (toDepartment.value == "") {
            toDepartment.focus();
            swal({
                title: 'Warning',
                text: 'Please select department.',
                type: 'warning',
                timer: 1000
            })
            return false;
        }
        if (toSection.value == "") {
            toSection.focus();
            swal({
                title: 'Warning',
                text: 'Please select section.',
                type: 'warning',
                timer: 1000
            })
            return false;
        }
        if (toLoc.value == "") {
            toLoc.focus();
            swal({
                title: 'Warning',
                text: 'Please select location.',
                type: 'warning',
                timer: 1000
            })
            return false;
        }


        return true;

    }


    function checkDisposalDate() {
        var dat = $("input[name=date]").val();
        var assetId = $("#assetCodeId").find("option:selected").val();

        if (dat != "" && assetId > 0) {
            var date = formatDate(dat);
            $.ajax({
                url: '${root}/data/checkDisposalDate',
                data: {date: date, assetId: assetId},
                success: function (data) {
                    if (data > 0) {
                        var msg;
                        switch (data) {
                            case 1:
                                msg = "Please change Transfers date";
                                break;
                            case 2:
                                msg = "Please select date, after the Asset purchase or register date";
                                break;
                            case 3 :
                                msg = "Please select date, after the Asset transfer date";
                                break;
                            case 4 :
                                msg = "Please select date, after the Asset Damage date";
                                break;
                            case 5 :
                                msg = "Please select date, after the Asset Improvement date";
                        }
                        swal({
                            title: 'Warning',
                            text: msg,
                            type: 'warning',
                            timer: 1000
                        })
                        $("input[name=date]").val("");
                    }

                }

            });
        }
    }
</script>