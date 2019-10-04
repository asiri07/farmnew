<%--
  Created by IntelliJ IDEA.
  User: Dilusa
  Date: 4/16/2019
  Time: 2:30 PM
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
<div class="col-md-9 page">
    <form name="reminder" method="post">
        <div class="row" style="margin: 0">
            <legend>Reminders</legend>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Date</div>
                    <div class="col-md-6">
                        <div class='input-group date' style="width: 100%">
                            <input type='text'  required class="form-control" onchange="loadData()" style="height: 35px"
                                   name="date" id="date"/>
                            <span class="input-group-addon">
                                <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                        </div>
                    </div>

                    <div class="col-md-2"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4" style="padding-top: 10px">No. of Days</div>
                    <div class="col-md-6">
                        <input type="text" required style="width: 100%" maxlength="10" id="noOfDays"
                               onkeypress="return isNumberOnly(event);displayErrorMessage()"
                               name="noOfDays"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Category</div>
                    <div class="col-md-6">
                        <select style="width: 100%" name="category" id="category" onchange="loadData()">
                            <option value="0">---SELECT---</option>
                            <option value="1">Insurance</option>
                            <option value="2">Warranty</option>
                            <option value="3">Service Agreement</option>
                            <option value="4">Lease Asset</option>
                        </select>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
            <div class="col-md-6"></div>
        </div>
        <div class="row" style="padding-left: 10px;padding-right: 10px">
            <table id="tblVerification" style="overflow-y: auto" class="responstable" width="100%"
                   cellspacing="0">
                <thead>
                <tr id="tableTRHeader">
                    <th>Asset Code</th>
                    <th>Transaction No.</th>
                    <th>Date From</th>
                    <th>Mature/Due Date</th>
                    <th>Value</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>


        <div class="row" style="margin-top: 20px">
            <div class="col-md-2"></div>
            <div class="col-md-7">
                <div class="row" style="padding-top: 10px">
                    <div class="col-md-3"></div>

                    <div class="col-md-3">
                        <button type="button" onclick="runReminderReport()" class="btn btn-primary"
                                style="width: 100%">Print
                        </button>
                    </div>
                    <div class="col-md-3" style="margin-top: 2px">
                        <a class="btn btn-primary" style="width: 100%" href="${root}/report/reminder">New</a>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </form>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<form action="${root}/report/genarateReminder" id="formReminder" target="_blank" method="post">
    <input type="hidden" id="date1" name="date1"/>
    <input type="hidden" id="noDays" name="noDays"/>
    <input type="hidden" id="categoryType" name="categoryType"/>
</form>


<script type="text/javascript">

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true
        });
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
    $('input[type=text]').on('input', function(){
        loadData();
    });


    function loadData() {

        var dateFrom = $("input[name=date]").val();
        var today = formatDate(dateFrom);

        var noDays = $("#noOfDays").val();
        var categoryType = $("#category").find("option:selected").val();
        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $("#tblVerification").find("tr:not(:first)").remove();
        if (dateFrom != "") {

            if (categoryType == 2) { // warranty no need value coloumn

                $("#tableTRHeader").html('<th>Asset Code</th>\n' +
                    '                    <th>Transaction No.</th>\n' +
                    '                    <th>Date From</th>\n' +
                    '                    <th>Mature/Due Date</th>');
                if(noDays!=""){
                $.ajax({
                    url: '${root}/report/loadData/' + today + '/' + noDays + '/' + categoryType + '/' + userBranch,
                    success: function (data) {

                        for (var i = 0; i < data.length; i++) {
                            var date1 = formatDate(data[i].dateFrom);
                            var date2 = formatDate(data[i].dateTo);
                            var markup = "<tr><td><input type='text' readonly style='width: 99%' value='" + data[i].assetCode + "' name='assetCode'></td> <td><input type='text' readonly style='width: 90%' value='" + data[i].transactionNo + "' name='transactionNo'></td><td><input type='text'  style='width: 90%' value='" + date1 + "' readonly name='fromDate'></td><td><input readonly type='text' style='width: 90%' ' value='" + date2 + " ' name='toDate'></td></tr>";
                            $("table tbody").append(markup);
                        }
                    }
                })}
            }else if (categoryType == 4) { // Lease Asset no need From Date coloumn
                $("#tableTRHeader").html('<th>Asset Code</th>\n' +
                    '                    <th>Transaction No.</th>\n' +
                    '                    <th>Premium Date</th>\n' +
                    '                    <th>Premium</th>');
                if(noDays!=""){
                $.ajax({
                    url: '${root}/report/loadData/' + today + '/' + noDays + '/' + categoryType + '/' + userBranch,
                    success: function (data) {

                        for (var i = 0; i < data.length; i++) {
                            var dateTo = formatDate(data[i].dateTo);
                            var markup = "<tr><td><input type='text' readonly style='width: 99%' value='" + data[i].assetCode + "' name='assetCode'></td> <td><input type='text' readonly style='width: 90%' value='" + data[i].transactionNo + "' name='transactionNo'></td><td><input type='text'  style='width: 90%' value='" + dateTo + "' readonly name='toDate'></td><td><input readonly type='text' id='tableValue" + i+ "'style='width: 90%;text-align:right' value='" + data[i].value + "' name='value'></td></tr>";
                            $("table tbody").append(markup);
                            addComma("tableValue" + i);
                        }
                    }
                })}
            }else {
                // alert("other");
                $("#tableTRHeader").html('<th>Asset Code</th>\n' +
                    '                    <th>Transaction No.</th>\n' +
                    '                    <th>Date From</th>\n' +
                    '                    <th>Mature/Due Date</th>\n' +
                    '                    <th>Value</th>');
                if(noDays!=""){
                $.ajax({
                    url: '${root}/report/loadData/' + today + '/' + noDays + '/' + categoryType + '/' + userBranch,
                    success: function (data) {

                        for (var i = 0; i < data.length; i++) {
                            var date1 = formatDate(data[i].dateFrom);
                            var date2 = formatDate(data[i].dateTo);
                            var markup = "<tr><td><input type='text' readonly style='width: 99%' value='" + data[i].assetCode + "' name='assetCode'></td> <td><input type='text' readonly style='width: 90%' value='" + data[i].transactionNo + "' name='transactionNo'></td><td><input type='text'  style='width: 90%' value='" + date1 + "' readonly name='fromDate'></td><td><input readonly type='text' style='width: 90%' ' value='" + date2 + " ' name='toDate'></td><td><input readonly type='text' id='tableValue" + i + "' style='width: 90%' value='" + data[i].value + "' name='value'></td></tr>";
                            $("table tbody").append(markup);
                            addComma("tableValue" + i);
                        }
                    }
                })}
            }

        } else {
            swal({
                title: 'Warning',
                text: 'Please select Date !!!',
                type: 'warning',
                timer: 1000
            })
        }


    }

    function displayErrorMessage() {

        var dateFrom = $("input[name=date]").val();

        if (dateFrom == "") {

            swal({
                title: 'Warning',
                text: 'Please select Date !!!',
                type: 'warning',
                timer: 1000
            })
        }


    }

    function runReminderReport() {

        var dateFrom = $("input[name=date]").val();
        var today = formatDate(dateFrom);
        var noDays = $("#noOfDays").val();
        var categoryType = $("#category").find("option:selected").val();

        $("#date1").val(today);
        $("#noDays").val(noDays);
        $("#categoryType").val(categoryType);
        $("#formReminder").submit();

    }


    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        if (pageType == 1) {
            if (status == 'true') {
                window.location.href = "./physicalVerification";
                swal({
                    title: 'success!',
                    type: 'success',
                    timer: 1000
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                })
            }
        }
    });


    function loadExistingVerifications() {
        $("#tblVerification").find("tr:not(:first)").remove();
        var date = $("input[name=date]").val();
        var today = formatDate(date);
        var locCodeId = $("#locCodeId").find("option:selected").val();
        if (locCodeId != null) {
            $.ajax({
                url: '${root}/data/loadExitsVerifications/' + today + '/' + locCodeId,
                success: function (data) {

                    for (var i = 0; i < data.length; i++) {
                        var markup = "<tr><td><input type='hidden' name='detailId' value='" + data[i].insuranceValue + "'/> <input type='text' readonly style='width: 70%' value='" + data[i].transactionNo + "' name='detailCode'></td><td><input type='text' readonly style='width: 100%' value='" + data[i].insuranceType + "' name='description'></td><td><input type='text' style='width: 50%' value='" + data[i].balance + "' name='detailBalance'></td><td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
                        $("table tbody").append(markup);
                    }
                }
            });
        }
    }


</script>