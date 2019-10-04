<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/28/2017
  Time: 4:17 AM
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
    <form name="assetRevaluationForm"
          action="${root}/data/saveAssetRevaluation" method="post">
        <div class="row" style="margin: 0">
            <legend>Asset Revaluation</legend>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4"  style="padding-top: 10px">Location Code</div>
                    <div class="col-md-8">

                        <input id="locCodeId" name="" style="height:35px;width:100%" type="text" autocomplete="off" onchange="createAssetObject(),loadExistingVerifications()"
                               class="form-control"
                               placeholder="Enter 1st character">
                        <input id="getId" name="locationId" hidden>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-2"  style="padding-top: 10px">Date</div>
                    <div class="col-md-7">
                        <div class='input-group date' style="width: 100%">
                            <input type='text' onchange="loadExistingVerifications()" required class="form-control" style="height:35px"
                                   name="date"/>
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

        <div class="row">
            <div class="col-md-2"  style="padding-top: 10px">Asset Code</div>
            <div class="col-md-4">
                <input id="assetId" name="" style="height:35px;width:100%" type="text" autocomplete="off" onchange="loadAssetTable()"
                       class="form-control"
                       placeholder="Enter 1st character">
                <input id="getAssetId" name="getAssetIdName" hidden>
            </div>
            <div class="col-md-6"></div>
        </div>

        <div class="row" style="padding-left: 10px;padding-right: 10px">
            <table id="tblVerification" style="overflow-y: auto" class="responstable" width="100%"
                   cellspacing="0">
                <thead>
                <tr>
                    <th style="width: 20%">Asset Code</th>
                    <th style="width: 30%">Description</th>
                    <th style="width: 19%">Computer Value</th>
                    <th style="width: 19%">Revaluation Value</th>
                    <th style="width: 10%">Currency type</th>
                    <th style="width: 2%">Remove</th>

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
                    <div class="col-md-4">
                        <button type="button" onclick="runAssetRevaluationReport()" class="btn btn-primary"
                                style="width: 100%; font-size: 14px;">Print
                        </button>
                    </div>
                    <div class="col-md-4" style="margin-top: 2px">
                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top:1px;"
                           href="${root}/data/assetRevaluation">New</a>
                    </div>
                    <div class="col-md-4">
                        <button onclick="costColumnCommaRemove();" class="btn btn-primary" id="btnSave"
                                style="width: 100%; font-size: 14px;">Save
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </form>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="maxRowNo" value="0">

<form action="${root}/report/genarateAssetRevaluation" id="formAssetRevaluation" target="_blank" method="post">
    <input type="hidden" id="locationId" name="locationId2"/>
    <input type="hidden" id="locationName" name="locationName"/>
</form>

<script type="text/javascript">

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
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

    function runAssetRevaluationReport() {
        var locCodeId = $("#getId").val();
        var locCode = $("#locCodeId").val();
        if (locCodeId != "") {
            $("#locationId").val(locCodeId);
            $("#locationName").val(locCode);
            $("#formAssetRevaluation").submit();
        } else {
            swal({
                title: 'Warning',
                text: 'Please select Location Code !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

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
                    window.location.href = "./assetRevaluation";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = ",/assetRevaluation"
                })
            }
        }
    });

    function loadAssetTable() {
        var detaiCatId = $("#getAssetId").val();
        var text = $("#assetId").val();
        var detaiCat = text.substring(0, ASSET_CODE_LENGTH).toUpperCase();

        var noOfUnit = $("#getAssetId").val();
        var date = $("input[name=date]").val();
        var maxRowNo = $("#maxRowNo").val();
        if (date != "") {
            if (detaiCatId > 0) {
                $.ajax({
                    url: '${root}/data/getAssetDetailCat/' + detaiCatId + '/' + detaiCat,
                    success: function (data) {

                        var markup = "<tr><td><input type='hidden' name='detailId' value='" + detaiCatId + "'/> <input type='text' readonly style='width: 99%' value='" + detaiCat + "' name='detailCode'></td>" +
                            "<td><input type='text' readonly style='width: 99%' value='" + data.asDes + "' name='description'></td>" +
                            "<td><input type='text' style='width: 99%;text-align: right' readonly name='detailBalance' id='detailBalanceId" + maxRowNo + "' onblur='addComma(id)' onkeyup='isNumber(this)' value='" + data.unitPrice + "'></td>" +
                            "<td><input type='text' style='width: 99%;text-align: right'  name='detailrevalue' id='detailrevalueId" + maxRowNo + "' onblur='addComma(id)' onkeyup='isNumber(this)' ></td>" +
                            "<td><select required  style='width: 98%;height:95%'  name='currencyId' id='currencyId" + maxRowNo + "' ><option value=''>SELECT</option></select></td>" +
                            "<td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
                        $("table tbody").append(markup);
                        addComma("detailBalanceId" + maxRowNo);
                        addComma("detailrevalueId" + maxRowNo);
                        loadCurrencyTypes("currencyId" + maxRowNo, data.currencyType);
                        maxRowNo++;
                        $("#maxRowNo").val(maxRowNo);
                        // $("#assetId option[value='" + detaiCatId + "']").remove();
                    }
                });
            }
        } else {
            $("input[name=date]").focus();
            swal({
                title: 'Warning',
                text: 'Please select Date !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    function loadExistingVerifications() {
        $("#tblVerification").find("tr:not(:first)").remove();
        var date = $("input[name=date]").val();
        var today = formatDate(date);
        var location = $("#getId").val();
        var maxRowNo = 0;
        if (date != "" && location != "") {
            $.ajax({
                url: '${root}/data/loadExitsRevaluation/' + today + '/' + location,
                success: function (data) {
                    if (data != "") {
                        for (var i = 0; i < data.length; i++) {
                            var markup = "<tr><td><input type='hidden' name='detailId' value='" + data[i].detailId + "'/> <input type='text' readonly style='width: 99%' value='" + data[i].detailCode + "' name='detailCode'></td>" +
                                "<td><input type='text' readonly style='width: 99%' maxlength='100' value='" + data[i].description + "' name='description'></td>" +
                                "<td><input type='text' style='width: 99%' maxlength='16' readonly value='" + data[i].balance + "' name='detailBalance' id='detailBalanceId" + i + "' onblur='addComma(id)' onkeyup='isNumber(this)'></td>" +
                                "<td><input type='text' style='width: 99%' maxlength='16' value='" + data[i].revalue + "' name='detailrevalue' id='detailrevalueId" + i + "' onblur='addComma(id)' onkeyup='isNumber(this)'></td>" +
                                // "<td><input type='text' readonly style='width: 99%' value='' name='detailCode'></td>" +
                                "<td><select required  style='width: 98%; height:95%'  name='currencyId' id='currencyId" + i + "' ><option value=''>SELECT</option></select></td>" +
                                // "<td><select required  style='width: 90%'  name='maintMaster' id='descriptionr'><option value='1'></option></select></td>"+
                                "<td style='padding-left: 20px'><a onclick=deleteRow('tblVerification',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
                            $("table tbody").append(markup);
                            addComma("detailBalanceId" + i);
                            addComma("detailrevalueId" + i);
                            loadCurrencyTypes("currencyId" + i, data[i].currencyId);
                            maxRowNo = i;
                            $("#maxRowNo").val(maxRowNo);

                        }
                        $("#btnSave").html('Update');
                    } else {
                        $("#btnSave").html('Save');
                    }
                }
            });
        }
    }

    function costColumnCommaRemove() {
        var max = $("#maxRowNo").val();
        for (i = 0; i <= max; i++) {
            var idName = "detailBalanceId" + i;
            var idVName = "detailrevalueId" + i;
            var detailBalanceId = "#" + idName;
            var detailrevalueId = "#" + idVName;
            if ($(detailBalanceId).length) {
                var cost = $(detailBalanceId).val();
                if (cost != "") {
                    $(detailBalanceId).val(cost.replace(/,/g, ''));
                }
            }

            if ($(detailrevalueId).length) {
                var cost = $(detailrevalueId).val();
                if (cost != "") {
                    $(detailrevalueId).val(cost.replace(/,/g, ''));
                }
            }
        }
    }

    //    $('#gridview1 tr:last').find('td:first').html()
    //
    //    function createRow(detaiCat) {
    //        var check = true;
    //      // var table = document.getElementById('tblVerification');
    //        var table = $("#tblVerification table tbody");
    //        table.find('tr').each(function (i, el) {
    //            var tds = $(this).find('td').text();
    //            var productId = tds.eq(0).text();
    //            alert(productId);
    ////            if(productId == detaiCat) {
    ////                check = false;
    ////            }
    //        });
    //        return check;
    //    }


    function loadAsset() {
        $("#tblVerification").find("tr:not(:first)").remove();
        $("#assetId").find("option:not(:first)").remove();
        var locCodeId = $("#getId").val();
        if (locCodeId > 0) {
            $.ajax({
                url: '${root}/data/getAssetByLocation/' + locCodeId,
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $("#assetId").append(
                            "<option value = '" + data[i][0] + "'>" + data[i][1]+"-"+data[i][2]
                            + "</option>");
                    }
                }
            });
        }
    }

    function deleteRow(tblName, link) {
        var tbl = document.getElementById(tblName);
        var tableRow = link.parentElement.parentElement;
        tbl.deleteRow(tableRow.rowIndex);
    }


    function loadCurrency(currencyId, id) {
        var idName = id.valueOf();
        var concatIdName = "#" + idName;

        var cur = currencyId;
        var currencyCode;
        $.ajax({
            url: '${root}/data/loadCurrencyCode/' + cur,
            success: function (data) {
                currencyCode = data;
                $(concatIdName).val(data);

            }
        });

    }

    function loadCurrencyTypes(id, number) {
        var idName = id.valueOf();
        var currrencyIdName = "#" + idName;
        $(currrencyIdName).find("option:not(:first)").remove();

        $.ajax({
            url: '${root}/data/getCurrencyCodes/',
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

createJsonObject();
var jsonArray = [];

function createJsonObject() {

    var userBranch = '<%= session.getAttribute("userBranch") %>';

    $.ajax({
        url: '${root}/data/loadLocationCode/' + userBranch,
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

$("#locCodeId").autocomplete({

    source: function (request, response) {
        var results = $.ui.autocomplete.filter(jsonArray, request.term);

        response(results.slice(0, 100));
    },

    select: function (event, ui) {

        document.getElementById("getId").value = ui.item.id;

    },
    delay: 0,
    minLength: 1,

});

var asset=[];
function createAssetObject() {
    asset=[];
    $("#tblVerification").find("tr:not(:first)").remove();
    $("#assetId").find("option:not(:first)").remove()

    var locCodeId = $("#getId").val();
    if (locCodeId > 0) {
        $.ajax({
            url: '${root}/data/getAssetByLocation/' + locCodeId,
            success: function (data) {


            var ten = data.length;

            for (var i = 0; i < ten; i++) {
                var arr2 = {};
                var id = data[i][0];
                var code = data[i][1] + " - " + data[i][2];
                arr2 = {id: id, value: code};

                asset.push(arr2);

            }
        }
    });

    }


}
$("#assetId").autocomplete({

    source: function (request, response) {
        var results = $.ui.autocomplete.filter(asset, request.term);

        response(results.slice(0, 100));
    },

    select: function (event, ui) {

        document.getElementById("getAssetId").value = ui.item.id;

    },
    delay: 0,
    minLength: 1,

});

</script>