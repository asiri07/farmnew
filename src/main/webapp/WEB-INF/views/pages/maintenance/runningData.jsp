<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
  Date: 01/25/2019
  Time: 8.40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--CSS & JS Files of timepicker--%>

<link rel="stylesheet" href="${root}/resources/css/jquery.timepicker.min.css">
<link rel="stylesheet" href="${root}/resources/css/jquery.timepicker.css">

<script src="${root}/resources/js/jquery.timepicker.min.js"></script>
<script src="${root}/resources/js/jquery.timepicker.js"></script>


<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>--%>
<%--<link rel="stylesheet" href="dist/css/timepicker.css">--%>
<%--<script src="dist/js/timepicker.js"></script>--%>


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

    .bootstrap-timepicker-widget.dropdown-menu {
        z-index: 1050 !important;
    }
    .ui-autocomplete {
        max-height: 200px;
        max-width: 350px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }

</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>
<!-- Ends -->
<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Running Data</legend>
    </div>
    <div class="row">&nbsp&nbsp</div>
    <div class="row">
        <div class="col-md-12">
            <form name="runningDataForm" action="${root}/maintenance/saveRunningData" method="post">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-3" style="padding-top: 10px"> Asset Code</div>
                            <div class="col-md-7">
                                <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text"
                                       autocomplete="off"
                                       class="form-control"
                                       placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()"
                                       onchange="refreshTable()">
                                <input id="getId" name="assetId" hidden>
                            </div>
                            <div class="col-md-2">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-3" style="padding-top: 10px"> Transaction No.</div>
                            <div class="col-md-7">
                                <select id="transactionSelectId" style="width: 100%"
                                        onchange="loadRunningData()"
                                        name="transactionSelect">
                                    <option value="0">---SELECT---</option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row" style="padding-left: 10px;padding-right: 10px">
                    <table id="tblRunning" style="overflow-y: auto" class="responstable" width="100%"
                           cellspacing="0">
                        <thead>
                        <tr class="tdCenter">
                            <th width="12%">Date</th>
                            <th width="12%">Time</th>
                            <th width="15%">Meter From</th>
                            <th width="15%">Meter To</th>
                            <th width="10%">Currency</th>
                            <th width="15%">Cost</th>
                            <th width="15%">Comments</th>
                            <th width="6%" style="text-align: center"><a onclick=addRow('tblRunning')><i
                                    class="fa fa-plus fa-2x"></i></a></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>

                <div class="row" style="margin-top: 20px">
                    <div class="col-md-6"></div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5"></div>
                            <div class="col-md-7">
                                <div class="row" style="padding-top: 10px">
                                    <div class="col-md-6" style="margin-top: 3px">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                           href="${root}/maintenance/runningData">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button onclick="costColumnCommaRemove()" class="btn btn-primary" id="btnSave"
                                                style="width: 100%; font-size: 14px">
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="runningId" id="runningId" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}">
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="maxRowNo" value="0">
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>


<script type="text/javascript">

    createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

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

    $('.time').timepicker({
        uiLibrary: 'bootstrap4'
    });

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Saved Successfully!!! Transaction No : ";
        } else {
            message = "Update Successfully!!! ";
        }

        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message + transactionNo,
                    type: 'success',

                }).then(function () {
                    window.location.href = "./runningData";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./runningData";
                })
            }
        }
    });


    function deleteRow(tblName, link) {
        var tbl = document.getElementById(tblName);
        var tableRow = link.parentElement.parentElement;
        tbl.deleteRow(tableRow.rowIndex);
        isDelete = 1;
    }


    function applyDatePicker() {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });
    }

    var isDelete = 0;

    function applyTimePicker(id) {
        var idName = id.valueOf();
        var timeIdName = "#" + idName;
        $(timeIdName).find("option:not(:first)").remove();
        $(timeIdName).timepicker({
            'step': function (i) {
                return (i % 2) ? 15 : 45;
            }
        });

    }


    function loadCurrencyTypes(id, number) {
        var idName = id.valueOf();
        var currrencyIdName = "#" + idName;
        $(currrencyIdName).find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/getCurrencyCodes/',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $(currrencyIdName).append(
                        "<option value = '" + data[i].currencyId + "'>" + data[i].currencyCode
                        + "</option>");
                }
                if (number != undefined) {
                    document.getElementById(idName).value = number;
                }
            }
        });


    }

    function addRow(tableID) {
        var maxRowNo = $("#maxRowNo").val();
        var assetId = $("#getId").val();
        if (assetId > 0) {
            var markup = "<tr >" +
                " <td  class='input-group date' style='width:10%' ><div class='input-group date' style=\"width: 100%\">\n" +
                " <input type='text' required class=\"form-control\"\n" +
                " id='date" + maxRowNo + "' name='date' style=\"height:90%;\" onchange='checkFromDate(id)'/>\n" +
                " <span class=\"input-group-addon\">\n" +
                "                                            <%--<button style=height:25px;width:30px> <i style=\"font-size:15px;color:#3e71fb\" class=\"fa fa-calendar\"></i></button>--%>\n" +
                "                                        <%--<span class=\"glyphicon glyphicon-calendar\"></span>--%>\n" +
                " <span style=\"font-size:24px;color:#3e71fb\" class=\"fa fa-calendar\"></span>\n" +
                " </span>\n" +
                " </div></td>" +
                " <td><input type='time' class='form-control' name='time' id='timeId" + maxRowNo + "'  style='width: 98%;height:30px;font-size:15px;' /></td>" +
                " <td><input type='text' style='width: 98%;height:95%' required maxlength='15' class='form-control' name='detailMetFroNo' id='detailMetFroNoId" + maxRowNo + "'  onkeypress='return isNumberOnly(event)'/></td>" +
                " <td><input type='text' class='form-control' required style='width:98%;height:95%' maxlength='15' name='detailMetToNo' id='detailmetToNoId" + maxRowNo + "' onblur='return checkMeter(id," + maxRowNo + ")' onkeypress='return isNumberOnly(event)'/></td>" +
                " <td><select required  style='width: 98%;height:95%'  required name='detailCurrencyType' id='detailCurrencyTypeId" + maxRowNo + "'><option value=''>SELECT</option></select></td>" +
                " <td><input type='text' style='width: 98%;height:95%;text-align:right' class='form-control' maxlength='17' name='detailCost' id='detailCostId" + maxRowNo + "' onblur='addComma(id)' onkeyup='isNumber(this)'/></td>" +
                " <td><input type='text' style='width: 98%;height:95%' class='form-control' maxlength='100' name='remark' id='remarkId' /></td>" +
                " <td style='text-align: center'><a onclick=deleteRow('tblRunning',this)><i  class=\"fa fa-trash-o fa-2x\"></i></a><input style='width: 90%'type='hidden' name='runningId' value='" + 0 + "'  /></td>" +
                "</tr>";
            $("table tbody").append(markup);
            loadCurrencyTypes("detailCurrencyTypeId" + maxRowNo, '');
            maxRowNo++;
            $("#maxRowNo").val(maxRowNo);

            applyDatePicker();
        } else {
            $("input[name=assetId]").focus();
            swal({
                title: 'Warning',
                text: 'Please select AssetId !!',
                type: 'warning',
                timer: 1000
            })
        }
    }


    function refreshTable() {
        $("#tblRunning").find("tr:not(:first)").remove();
    }

    function checkMeter(id, i) {
        var idNameTo = id.valueOf();
        var meterTo = "#" + idNameTo;
        var idNameFrom = "detailMetFroNoId" + i;
        var meterFrom = "#" + idNameFrom;
        var meterToValue = $(meterTo).val();
        var meterFromValue = $(meterFrom).val();

        if (Number(meterToValue) < Number(meterFromValue)) {
            msg = "Meter To Value should maximize";
            swal({
                title: 'Warning',
                text: msg,
                type: 'warning',
                timer: 1000
            });
            $(meterTo).val("");
        }
    }

    function loadTransactionNoList() {
        var assetId = $("#getId").val();
        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadRunningDataNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data.runningId + "'>" + data[i]
                        + "</option>");
                }
                $("#btnSave").html('Save');
                document.getElementById("transactionSelectId").selectedIndex = 0;
            }
        });
    }

    function costColumnCommaRemove() {
        var max = $("#maxRowNo").val();
        for (i = 0; i <= max; i++) {
            var idName = "detailCostId" + i;
            var currrencyIdName = "#" + idName;
            if ($(currrencyIdName).length) {
                var cost = $(currrencyIdName).val();
                if (cost != "") {
                    $(currrencyIdName).val(cost.replace(/,/g, ''));
                }
            }
            else {

            }
        }
    }


    function loadRunningData() {
        refreshTable();
        var transactionNo = $("#transactionSelectId").find("option:selected").text();
        // var maxRowNo = $("#maxRowNo").val();
        var maxRowNo = 0;
        $.ajax({
            url: '${root}/maintenance/loadRunningDataByTno/' + transactionNo,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var markup = "<tr>" +
                        " <td  class='input-group date' style='width:10%' >   <div class='input-group date' style=\"width: 100%\">\n" +
                        " <input type='text' required class=\"form-control\"\n" +
                        " id='date" + i + "' name=\"date\" style=\"height:90%;\" onchange='checkFromDate(id)' value='" + data[i].date + "'/>\n" +
                        " <span class=\"input-group-addon\">\n" +
                        "                                            <%--<button style=height:25px;width:30px> <i style=\"font-size:15px;color:#3e71fb\" class=\"fa fa-calendar\"></i></button>--%>\n" +
                        "                                        <%--<span class=\"glyphicon glyphicon-calendar\"></span>--%>\n" +
                        " <span style=\"font-size:24px;color:#3e71fb\" class=\"fa fa-calendar\"></span>\n" +
                        " </span>\n" +
                        " </div></td>" +
                        " <td> <input class='form-control' type='time' style='width: 98%;height:30px;font-size:15px;' name='time' id='timeId" + i + "' value='" + data[i].time + "'> </td>" +
                        " <td class='class='form-control''><input type='text' maxlength='15' required  style='width: 98%;height:95%' name='detailMetFroNo' id='detailMetFroNoId" + i + "' value='" + data[i].metFroNo + "'></td>" +
                        " <td class='class='form-control''><input type='text' maxlength='15' required  style='width: 98%;height:95%' name='detailMetToNo' id='detailmetToNoId" + i + "' onblur=\"return checkMeter(id," + i + ")\" value='" + data[i].metToNo + "'></td>" +
                        " <td><select required  style='width: 98%;height:95%' required name='detailCurrencyType' id='detailCurrencyTypeId" + i + "' ><option value=''>SELECT</option></select></td>" +
                        " <td><input type='text' maxlength='17' required style='width: 98%;height:95%;text-align:right'class='form-control' onblur='addComma(id)' onkeyup='isNumber(this)' name='detailCost' id='detailCostId" + i + "' value='" + data[i].cost + "'></td>" +
                        " <td><input type='text' maxlength='100' style='width: 98%;height:95%' class='form-control' name='remark' value='" + data[i].remark + "'></td>" +
                        " <td style='text-align: center'><input style='width: 98%;height:95%'type='hidden' name='runningId'  value='" + data[i].runningId + "'/></td>" +
                        " </tr>";
                    $("table tbody").append(markup);
                    maxRowNo++;
                    $("#maxRowNo").val(maxRowNo);
                    loadCurrencyTypes("detailCurrencyTypeId" + i, data[i].currencyType);
                    addComma("detailCostId" + i);
                    applyDatePicker();
                    $("#btnSave").html('Update');
                    // loadCurrencyName(data[i].currencyType, i.toString());
                    // $("input[name=maintenanceDataId]").val(data.maintenanceDataId);
                }
            }
        });
    }

    function checkFromDate(id) {
        var idName = id.valueOf();
        var dateId = "#" + idName;
        var periodFrom = $(dateId).val();
        var assetId = $("#getId").val();
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
                            $(dateId).val("");
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
            $(dateId).val("");
        }
    }

    function loadCurrencyName(currencyId, id) {
        var idName = id.valueOf();
        var concatIdName = "#" + idName;
        $.ajax({
            url: '${root}/maintenance/getCurrencyName/' + currencyId,
            success: function (data) {
                $(concatIdName).val(data.currencyCode);
            }
        });
    }

    function checkAssetCodeValid() {
        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    // $('#assetCodeTo').removeClass('is-invalid');
                    loadTransactionNoList();
                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');

                } else {
                    $('#btnSave').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                }
            }
        });
    }

    function isEmpty() {

        if (document.getElementById("assetCodeTo").value == '') {
            $('#assetCodeTo').css('border-color', '#aaaaaa');

        }
    }


    var jsonArray = [];

    function createJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadAssetByAssetCode/' + userBranch,
            success: function (data) {

                var ten = data.length;

                for (var i = 0; i < ten; i++) {
                    var arr2 = {};
                    var id = data[i][0];
                    var code = data[i][1] + " - " + data[i][2];
                    arr2 = {id: id, value: code};

                    jsonArray.push(arr2);

                }
            }
        });

    }

    $("#assetCodeTo").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("getId").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

</script>