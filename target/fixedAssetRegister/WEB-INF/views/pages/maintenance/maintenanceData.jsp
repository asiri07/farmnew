<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
  Date: 01/18/2019
  Time: 4:42 PM
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
        <legend>Maintenance Data</legend>
    </div>
    <div class="row">&nbsp</div>
    <div class="row">
        <div class="col-md-12">
            <form name="maintenanceDataForm" action="${root}/maintenance/saveMaintenanceData" method="post">
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
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-3" style="padding-top: 10px"> Transaction No.</div>
                            <div class="col-md-7">
                                <select id="transactionSelectId" class="chosen" style="width: 100%"
                                        onchange="loadMaintenanceData()"
                                        name="transactionSelect">
                                    <option value="0">---SELECT---</option>
                                </select>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">

                            <div class="col-md-3" style="padding-top: 10px">Entry Date</div>
                            <div class="col-md-7">

                                    <input type='text' required
                                           name="maintEntryDaId" id="maintEntryDaId" style="height:35px;width:100%" readonly>


                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6">

                    </div>
                </div>


                <div class="row" style="padding-left: 10px;padding-right: 10px">
                    <table id="tblMaintenance" style="overflow-y: auto" class="responstable" width="100%"
                           cellspacing="0">
                        <thead>
                        <tr style="height:20%">
                            <th width="12%">Modified Date</th>
                            <th width="30%">Work Done</th>
                            <th width="12%">Meter Reading</th>
                            <th width="12%">Accessories</th>
                            <th width="9%">Currency</th>
                            <th width="12%">Cost</th>
                            <th width="8%">A/C Code</th>
                            <th width="5%"><a onclick=addRow('tblMaintenance')><i class="fa fa-plus fa-2x"></i></a></th>
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
                                           href="${root}/maintenance/maintenanceData">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button class="btn btn-primary" style="width: 100%; font-size: 14px"
                                                id="btnSave"
                                                onclick="costColumnCommaRemove()">
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="maintenanceDataId" id="maintenanceDataId"
                       value="${maintenanceData.maintenanceDataId}">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}">
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="maxRowNo" value="0">


<script type="text/javascript">

    getDate();
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

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "success!!! Transaction No : ";
        } else {
            message = "Update success!!! ";
        }

        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message + transactionNo,
                    type: 'success',

                }).then(function () {
                    window.location.href = "./maintenanceData";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000,

                }).then(function () {
                    window.location.href = "./maintenanceData";
                })
            }
        }
    });

    // Load relevant Accessories Names into combo box when selected an asset number

    var isDelete = 0;

    function loadAccessories(id, number) {
        var idName = id.valueOf();
        var accessoryIdName = "#" + idName;
        var assetId = $("#getId").val();
        $(accessoryIdName).find("option:not(:first)").remove();

        $.ajax({
            url: '${root}/category/getAccessory/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {

                    $(accessoryIdName).append(
                        "<option value = '" + data[i].accerCode + "'>" + data[i].accerName
                        + "</option>");
                }
                $(accessoryIdName).append("<option value ='Other'>" + "Other" + "</option>");
                document.getElementById(idName).value = number;
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


    function deleteRow(tblName, link) {
        var tbl = document.getElementById(tblName);
        var tableRow = link.parentElement.parentElement;
        tbl.deleteRow(tableRow.rowIndex);
        isDelete = 1;
    }

    // Getting Current Date for "Entry Date" Field
    function getDate() {
        var date = new Date();
        // document.getElementById("maintEntryDaId").value =  date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
        document.getElementById("maintEntryDaId").value = dateFormators(date);
    }


    function applyDatePicker() {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });

    }

    function addRow(tableID) {
        var assetId = $("#getId").val();
        // var tbl = document.getElementById("#tblMaintenance");
        var tableRow = $(this).parent("#tblMaintenance").index();
        var maxRowNo = $("#maxRowNo").val();

        if (assetId > 0) {
            var markup = "<tr >" +
                " <td  class='input-group date' style='width:10%'><div class='input-group date' style=\"width: 100%\">\n" +
                "<input type='text' required class=\"form-control\" id='date" + maxRowNo + "' onchange='checkFromDate(id)'\n" +
                " name=\"maintDa\" style=\"height:100%;\" />\n" +
                "<span class=\"input-group-addon\">\n" +
                "                                            <%--<button style=height:25px;width:30px> <i style=\"font-size:15px;color:#3e71fb\" class=\"fa fa-calendar\"></i></button>--%>\n" +
                "                                        <%--<span class=\"glyphicon glyphicon-calendar\"></span>--%>\n" +
                " <span style=\"font-size:24px;color:#3e71fb\" class=\"fa fa-calendar\"></span>\n" +
                " </span>\n" +
                " </div></td>" +
                "<td ><textarea rows='1' maxlength='200' style='width: 98%;height:6vh;font-size:15px;' wrap='soft' name='maintWork'></textarea></td>" +
                "<td  class'class='form-control''><input required type='text' maxlength='16'  style='width:98%;height:100%;'class='form-control' name='detailMaintMeter' onkeypress=\"return isNumberOnly(event)\" ></td>" +
                "<td ><select required  style='width: 98%;height:95%' required name='maintMaster' id='maintMasterId" + maxRowNo + "'><option value=''>SELECT</option></select></td>" +
                "<td ><select required  style='width: 98%;height:95%' required name='detailCurrencyType' id='detailCurrencyTypeId" + maxRowNo + "' ><option value=''>SELECT</option></select></td>" +
                "<td  ><input type='text' maxlength='16' style='width: 98%;height:95%;text-align:right'class='form-control' required id='detailMaintCostId" + maxRowNo + "' name='detailMaintCost' onblur='addComma(id)' onkeyup='isNumber(this)'></td>" +
                "<td ><input type='text' maxlength='11' style='width: 98%;height:95%' class='form-control'required  name='maintAcCode' ></td>" +
                "<td  style='text-align: center'><a onclick=deleteRow('tblMaintenance',this)><i  class=\"fa fa-trash-o fa-2x\"></i></a><input style='width: 90%'type='hidden' name='maintenanceDataId' value='" + 0 + "'  /></td>" +
                "</tr>";
            $("table tbody").append(markup);
            applyDatePicker();
            loadAccessories("maintMasterId" + maxRowNo, '');
            loadCurrencyTypes("detailCurrencyTypeId" + maxRowNo, '');
            checkFromDate("date" + maxRowNo);
            maxRowNo++;
            $("#maxRowNo").val(maxRowNo);
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
        $("#tblMaintenance").find("tr:not(:first)").remove();
    }


    function loadTransactionNoList() {

        var assetId = $("#getId").val();

        // $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadMaintenanceDataNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data.maintenanceDataId + "'>" + data[i]
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
            var idName = "detailMaintCostId" + i;
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

    function loadMaintenanceData() {
        var transactionNo = $("#transactionSelectId").find("option:selected").text();
        refreshTable();
        var maxRowNo = 0;
        // var maxRowNo = $("#maxRowNo").val();
        $.ajax({
            url: '${root}/maintenance/loadMaintenanceDataByTno/' + transactionNo,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {

                    var markup = "<tr >" +
                        " <td  class='input-group date' style='width:10%' >   <div class='input-group date' style=\"width: 100%\">\n" +
                        " <input type='text' required class=\"form-control\"\n id=\"date\" name=\"maintDa\" style=\"height:90%;\"  + value='" + data[i].maintDa + "'/>" +
                        " <span class=\"input-group-addon\">\n" +
                        "                                            <%--<button style=height:25px;width:30px> <i style=\"font-size:15px;color:#3e71fb\" class=\"fa fa-calendar\"></i></button>--%>\n" +
                        "                                        <%--<span class=\"glyphicon glyphicon-calendar\"></span>--%>\n" +
                        " <span style=\"font-size:24px;color:#3e71fb\" class=\"fa fa-calendar\"></span>\n" +
                        " </span>\n </div></td>" +
                        " <td><textarea rows='1' maxlength='200' style='width: 98%;height:6vh;font-size:15px;' wrap='soft' name='maintWork'>" + data[i].maintWork + "</textarea> </td>" +
                        " <td class='class='form-control''><input type='text' maxlength='15' required style='width: 98%;height:95%' name='detailMaintMeter'  onkeypress=\"return isNumberOnly(event)\"  value='" + data[i].maintMeter + "'></td>" +
                        " <td><select required  style='width: 98%;height:95%'  name='maintMaster' id='maintMasterId" + i + "' ><option value=''>SELECT</option></select></td>" +
                        " <td><select required  style='width: 98%;height:95%' required name='detailCurrencyType' id='detailCurrencyTypeId" + i + "' ><option value=''>SELECT</option></select></td>" +
                        " <td><input type='text' maxlength='16' required style='width: 98%;height:95%;text-align:right'class='form-control' id='detailMaintCostId" + i + "' name='detailMaintCost'  onblur=\"return addComma(id)\" onkeypress=\"return isNumberAndDecimalOnly(event)\" value='" + data[i].maintCost + "'></td>" +
                        " <td><input type='text' maxlength='11' required style='width: 98%;height:95%' class='form-control' name='maintAcCode' value='" + data[i].maintAcCode + "'></td>" +
                        " <td style='text-align: center'><input style='width: 90%'type='hidden' name='maintenanceDataId'  value='" + data[i].maintenanceDataId + "'/></td>" +
                        " </tr>";
                    $("table tbody").append(markup);
                    maxRowNo++;
                    $("#maxRowNo").val(maxRowNo);
                    loadAccessories("maintMasterId" + i, data[i].maintMaster);
                    loadCurrencyTypes("detailCurrencyTypeId" + i, data[i].currencyType);
                    addComma("detailMaintCostId" + i);
                    applyDatePicker();
                    $("#btnSave").html('Update');
                }
            }
        });

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


    function checkFromDate(id) {
        var idName = id.valueOf();
        var dateId = "#" + idName;
        var assetId = $("#getId").val();
        var periodFrom = $(dateId).val();
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