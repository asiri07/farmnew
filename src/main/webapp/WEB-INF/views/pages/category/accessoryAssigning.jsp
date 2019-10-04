<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
  Date: 2019/02/06
  Time: 8.15AM
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
        max-width:350px;
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

<div class="col-md-11 page">
    <form name="accessoryAssigningForm" action="${root}/category/saveAccessoryAssigning" method="POST">
        <div class="row" style="margin: 0">
            <legend>Accessory Assigning</legend>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                    <div class="col-md-8">

                        <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                               class="form-control" placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                        <input id="getId" name="assetId" hidden>
                    </div>
                </div>
            </div>
            <div class="col-md-6"></div>
        </div>

        <div class="row">
            <div class="col-md-2" style="padding-top: 10px">Accessory</div>
            <div class="col-md-4">
                <select type="text" name="accessoryId" id="accessoryCode" required style="width: 100%"
                        onchange="loadAccessoryTable()">
                    <option value="0">---SELECT---</option>

                </select>
            </div>
            <div class="col-md-6"></div>
        </div>

        <div class="row" style="padding-left: 10px;padding-right: 10px">
            <table id="accessoryTable" style="overflow-y: auto" class="responstable" width="100%"
                   cellspacing="0">
                <thead>
                <tr>
                    <th style="width:30%">Accessory Code</th>
                    <th style='width:50%'>Accessory Name</th>
                    <th style='width:15%'>Qty</th>
                    <th>Delete</th>
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
                    <div class="col-md-4" style="margin-top: 1px">
                        <button type="button" onclick="runAccessoryAssigningReport()" class="btn btn-primary"
                                style="width: 100%;height: 30px; font-size: 14px">Print
                        </button>
                    </div>
                    <div class="col-md-4" style="margin-top: 1px">
                        <a class="btn btn-primary" style="width: 100%; margin-top:3px;height: 30px; font-size: 14px"
                           href="${root}/category/accessoryAssigning">New</a>
                    </div>
                    <div class="col-md-4" style="margin-top: 1px" >
                        <button type="submit" class="btn btn-primary" style="width: 100%;height: 30px; font-size: 14px" id="btnSave" >Save
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>

    </form>
</div>
<%--<div class="col-md-5">--%>
<%--<table id="dataTable" class="display compact" width="100%" cellspacing="0">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>Asset Code</th>--%>
<%--<th>Accessory Name</th>--%>
<%--<th>Edit</th>--%>
<%--<th>Delete</th>--%>
<%--</tr>--%>
<%--</thead>--%>

<%--<tbody>--%>
<%--<c:forEach var="accessoryDetails" items="${accessoryDetails}">--%>
<%--<tr id="${accessoryDetails.accessoryId}">--%>
<%--<td>${accessoryDetails.assetCode}</td>--%>
<%--<td>${accessoryDetails.accessoryName}</td>--%>
<%--<td><a ><i--%>
<%--class="fa fa-pencil-square-o"></i></a></td>--%>
<%--<td><a onclick="onDelete(this)"><i class="fa fa-trash-o"></i></a></td>--%>

<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--&lt;%&ndash;href="${root}/category/editMainCategory/${mainCategory.mcatId}"&ndash;%&gt;--%>
<%--</div>--%>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>

<form action="${root}/report/genarateAccessoryAssigning" id="formAccessoryAssigning" target="_blank" method="post">
    <input type="hidden" id="assetId" name="assetId"/>
    <input type="hidden" id="assetDesc" name="assetDesc"/>
</form>

<script type="text/javascript">



    createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });
    });

    $(document).ready(function () {
        $('#dataTable').DataTable({
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false
        });
    });


    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('.btnSubmit').click(function () {
        $(".se-pre-con").show();
    });

    function runAccessoryAssigningReport() {
        var assetId = $("#getId").val();
        var assetDesc = $("#assetCodeTo").val();

        if (assetId != "") {
            $("#assetId").val(assetId);
            $("#assetDesc").val(assetDesc);
            $("#formAccessoryAssigning").submit();
        } else {
            swal({
                title: 'Warning',
                text: 'Please select Asset Code !!',
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
                    window.location.href = "./accessoryAssigning";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./accessoryAssigning";
                })
            }
        }
    });

    function loadExistingAccessories() {

        $("#accessoryTable").find("tr:not(:first)").remove();
        // var assetId = $("#assetCode").find("option:selected").val();
        var assetId = $("#getId").val();

        if (assetId > 0) {
            $.ajax({
                url: '${root}/category/getAccessory/' + assetId,
                success: function (data) {

                    if(data.length>0) {
                        $("#btnSave").html('Update');
                    }
                    for (var i = 0; i < data.length; i++) {
                        var markup = "<tr >" +
                            " <td><input type='hidden' style='width: 90%' name='detailAccessoryId' value='" + data[i].accerId + "'><input type='text' readonly style='width: 90%' value='" + data[i].accerCode + "'></td>" +
                            " <td><input type='text' readonly style='width: 90%' value='" + data[i].accerName + "'></td>" +
                            " <td><input type='text' style='width: 90%' value='" + data[i].qty + "'></td>" +
                            " <td><a onclick=deleteRow('accessoryTable',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td>" +
                            "</tr>";
                        $("#accessoryTable").append(markup);
                    }

                }
            });
        }
        else {
            $("input[name=assetId]").focus();
            swal({
                title: 'Warning',
                text: 'Please select AssetId !!',
                type: 'warning',
                timer: 1000
            })
        }
    }

    function loadAllAccessories() {
        $("#accessoryCode").find("option:not(:first)").remove();
        // var assetId = $("#assetCode").find("option:selected").val();
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/category/getAllAccessories/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#accessoryCode").append(
                        "<option value = '" + data[i].accerId + "'>" + data[i].accerCode + "--" + data[i].accerName
                        + "</option>");
                }
            }
        });
    }

    function loadAccessoryTable() {
        var accerId = $("#accessoryCode").find("option:selected").val();
        var accerCode = $("#accessoryCode").find("option:selected").text();
        var array = accerCode.split("--");

        var markup = "<tr><td><input style='width: 90%'type='hidden' name='detailAccessoryId'  value='" + accerId + "'/> " +
            "<input style='width: 90%'type='text' readonly value='" + array[0] + "'/></td>" +
            "<td><input type='text'  style='width: 90%' value='" + array[1] + "'></td>" +
            "<td><input type='text'  style='width: 90%' value='1'></td>" +
            "<td><a onclick=deleteRow('accessoryTable',this)><i  class=\"fa fa-trash-o fa-3x\"></i></a></td></tr>";
        $("#accessoryTable").append(markup);

        // ********* If I uncomment one of following lines then Save function is not working******
        //    change as Asset Physical Verification Or Revaluation **** 2019-06-18 ** Assing to Dilusha
        // $("#accessoryCode").find("option:selected").remove();
        // $("#accessoryCode option[value='" + accerId + "']").remove();

    }

    function deleteRow(tblName, link) {
        var tbl = document.getElementById(tblName);
        var tableRow = link.parentElement.parentElement;
        tbl.deleteRow(tableRow.rowIndex);
    }

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
                url: '${root}/category/deleteAccessoryAssigning/' + trId,
                success: function (data) {
                    if (data == 1) {
                        swal({
                            title: 'Deleted!',
                            text: 'Your file has been deleted.',
                            type: 'success',
                            timer: 1000
                        })
                        var row = id.parentNode.parentNode;
                        row.parentNode.removeChild(row);
                    } else {
                        swal({
                            title: 'Sorry!',
                            text: 'Can\'t Delete Accessory Assignning!!!',
                            type: 'warning',
                            timer: 1000
                        })
                    }
                }
            });
        })
    }

    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>

            <%--if (assetCodeIdval == '') {--%>
                <%--assetCodeIdval = 0;--%>
            <%--}--%>

            <%--$.ajax({--%>
                <%--url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval,--%>
                <%--success: function (data) {--%>
                    <%--assetCodeIdval = assetCodeIdval == "0" ? "" : assetCodeIdval;--%>
                    <%--$("#listasset").html('<input  autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"\n' +--%>
                        <%--'               onkeydown="loadAssetByType()"' +--%>
                        <%--'               onblur="checkAssetCodeValid()" value="' + assetCodeIdval + '" ' +--%>
                        <%--'               id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control "/>');--%>
                    <%--$("#divAssetList").html('');--%>
                    <%--var ten = 100;--%>
                    <%--if (data.length < 100) {--%>
                        <%--ten = data.length;--%>
                    <%--}--%>
                    <%--for (var i = 0; i < ten; i++) {--%>
                        <%--$("#divAssetList").append("<option selected value = '" + data[i][1] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");--%>
                    <%--}--%>
                    <%--$('#assetCodeUserId').putCursorAtEnd();--%>
                <%--}--%>
            <%--});--%>
            <%--$(".chosen").chosen();--%>
        <%--}, 10);--%>
    <%--}--%>

    <%--function checkAssetCodeValid() {--%>
        <%--var assetCode = $('input[name=assetCodeUser]').val();--%>
        <%--$.ajax({--%>
            <%--url: '${root}/data/checkAssetCodeValid/' + assetCode,--%>
            <%--success: function (data) {--%>
                <%--if (data != 0) {--%>
                    <%--$('#assetCodeUserId').removeClass('is-invalid');--%>
                    <%--$('#assetCode').val(data);--%>
                    <%--$('#btnSave').attr("disabled", false);--%>
                    <%--loadExistingAccessories();loadAllAccessories();--%>
                <%--} else {--%>
                    <%--$('#assetCode').val(0);--%>
                    <%--$('#btnSave').attr("disabled", true);--%>
                    <%--$('#assetCodeUserId').addClass('is-invalid');--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>


    function checkAssetCodeValid() {

        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    loadAllAccessories(); loadExistingAccessories();
                } else {
                    // $('#assetCodeId').val(0);
                    $('#btnSave').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                    // $('#assetCodeUserId').addClass('is-invalid');
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

    })


</script>