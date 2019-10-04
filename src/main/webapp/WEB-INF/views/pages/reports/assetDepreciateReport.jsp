<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 10/26/2017
  Time: 6:46 AM
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
<div class="row">
    <div class="col-md-12" style="width: 70%">
        <div class="page">
            <form name="" action="${root}/report/genarateWithDepreciation" target="_blank" method="post">

                <div class="row" style="margin: 0">
                    <legend>With Depreciation Values</legend>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Assets</div>
                            <div class="col-md-8" id="divAssetTo">
                                <%--<select style="width: 95%" required name="fromcord">--%>
                                <%--<option value="">---SELECT---</option>--%>
                                <%--<c:forEach var="asset" items="${assets}">--%>
                                <%--<option value="${asset.asId}">${asset.asCode}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                                <%--<datalist id="divAssetList">--%>
                                <%--</datalist>--%>

                                <%--<div id="listasset">--%>
                                <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                <%--onkeypress="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                <%--id="assetCodeFromUserId" name="assetCodeUser" placeholder="Search.." required--%>
                                <%--class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<input id="fromcord" name="fromcord" hidden>--%>
                                <input id="assetCodeFrom" name="getName1" style="height:35px;width:100%" type="text"
                                       autocomplete="off"
                                       class="form-control" data-maxitems="100"
                                       placeholder="Enter 1st character" onblur="checkAssetCodeValidFrom(),isEmptyFrom()">
                                <input id="fromcord" name="fromcord" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8" id="divAssetTo1">

                                <%--<datalist id="divAssetListToCode">--%>
                                <%--</datalist>--%>

                                <%--<div id="listassetToCode">--%>
                                <%--<input autoComplete="off" list="divAssetListToCode"--%>
                                <%--style="font-size: 15px; height: 35px"--%>
                                <%--onkeypress="loadAssetToCodeByType()" onblur="checkAssetToCodeValid()"--%>
                                <%--id="assetToCodeUserId" name="assetToCodeUser" placeholder="Search.." required--%>
                                <%--class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<input id="tocord" name="tocord" hidden>--%>
                                    <input id="assetCodeTo" name="getName2" style="height:35px;width:100%" type="text"
                                           autocomplete="off"
                                           class="form-control" data-maxitems="100"
                                           placeholder="Enter 1st character" onblur="checkAssetCodeValidTo(),isEmptyTo()">
                                    <input id="tocord" name="tocord" hidden>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Process Range</div>
                            <div class="col-md-8">
                                <input class="form-control" name="Fromdate" id="Fromdate" readonly style="width: 100%;height:105%"
                                       value="${fromDate}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-8">
                                <input class="form-control" name="Todate" id="Todate" readonly style="width: 100%;height:105%"
                                       value="${toDate}"/>
                            </div>
                            <div class="col-md-5"></div>
                        </div>

                    </div>
                </div>

                <div class="row" style="padding-top: 10px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a type="button" class="btn btn-primary"
                                   style="width: 100%;height: 30px; font-size: 14px; margin-top: 3px"
                                   href="${root}/report/withDepreciation">New</a>
                            </div>

                            <div class="col-md-4">
                                <c:choose>
                                    <c:when test="${doPreparate}">
                                        <button type="submit" disabled class="btn btn-primary"
                                                style="width: 100%;height: 30px; font-size: 14px">Print
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" id="btnPrint" class="btn btn-primary"
                                                style="width: 100%;height: 30px; font-size: 14px">Print
                                        </button>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </div>
                    <div class="col-md-6"></div>
                </div>

            </form>
        </div>
    </div>
</div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="doPreparate" value="${doPreparate}"/>

<script type="text/javascript">
    // loadAssetByType();
    // loadAssetToCodeByType();

    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    document.getElementById('assetCodeFrom').setAttribute('maxlength', ASSET_CODE_LENGTH);
    createJsonObject();
    $('#btnPrint').attr("disabled", true);
    $(window).load(function () {
        // Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });

    $('#btnSubmit').click(function () {
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
        if (status == 'true') {
            swal({
                title: 'Warning',
                text: 'Please do the Report Preparation!',
                type: 'warning',
                timer: 2000
            })
        }

    });

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
    <%--'               id="assetCodeFromUserId" name="assetCodeUser" placeholder="Search.." required class="form-control "/>');--%>
    <%--$("#divAssetList").html('');--%>
    <%--var ten = 100;--%>
    <%--if (data.length < 100) {--%>
    <%--ten = data.length;--%>
    <%--}--%>
    <%--for (var i = 0; i < ten; i++) {--%>
    <%--$("#divAssetList").append("<option selected value = '" + data[i][1] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");--%>
    <%--}--%>
    <%--$('#assetCodeFromUserId').putCursorAtEnd();--%>
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
    <%--$('#assetCodeFromUserId').removeClass('is-invalid');--%>
    <%--$('#fromcord').val(data);--%>
    <%--if ($('#Fromdate').val() == "") {--%>
    <%--$('#btnPrint').attr("disabled", false);--%>
    <%--} else {--%>
    <%--$('#btnPrint').attr("disabled", false);--%>
    <%--}--%>
    <%--// loadTransactionNoList();--%>
    <%--} else {--%>
    <%--$('#fromcord').val(0);--%>
    <%--$('#btnPrint').attr("disabled", true);--%>
    <%--$('#assetCodeFromUserId').addClass('is-invalid');--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function loadAssetToCodeByType() {--%>
    <%--setTimeout(function () {--%>
    <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
    <%--var assetCodeIdval = $('input[name=assetToCodeUser]').val().toUpperCase();--%>

    <%--if (assetCodeIdval == '') {--%>
    <%--assetCodeIdval = 0;--%>
    <%--}--%>

    <%--$.ajax({--%>
    <%--url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval,--%>
    <%--success: function (data) {--%>
    <%--assetCodeIdval = assetCodeIdval == "0" ? "" : assetCodeIdval;--%>
    <%--$("#listassetToCode").html('<input  autoComplete="off" list="divAssetListToCode" style="font-size: 15px; height: 35px"\n' +--%>
    <%--'               onkeydown="loadAssetToCodeByType()"' +--%>
    <%--'               onblur="checkAssetToCodeValid()" value="' + assetCodeIdval + '" ' +--%>
    <%--'               id="assetToCodeUserId" name="assetToCodeUser" placeholder="Search.." required class="form-control "/>');--%>
    <%--$("#divAssetListToCode").html('');--%>
    <%--var ten = 100;--%>
    <%--var i = 0;--%>
    <%--if (data.length < 100) {--%>
    <%--ten = data.length;--%>
    <%--}--%>
    <%--for (i; i < ten; i++) {--%>
    <%--$("#divAssetListToCode").append("<option selected value = '" + data[i][1] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");--%>
    <%--}--%>
    <%--$('#assetToCodeUserId').putCursorAtEnd();--%>
    <%--}--%>
    <%--});--%>
    <%--$(".chosen").chosen();--%>
    <%--}, 10);--%>
    <%--}--%>

    <%--function checkAssetToCodeValid() {--%>
    <%--var assetCode = $('input[name=assetToCodeUser]').val();--%>
    <%--$.ajax({--%>
    <%--url: '${root}/data/checkAssetCodeValid/' + assetCode,--%>
    <%--success: function (data) {--%>
    <%--if (data != 0) {--%>
    <%--$('#assetToCodeUserId').removeClass('is-invalid');--%>
    <%--$('#tocord').val(data);--%>
    <%--if ($('#Todate').val() == "") {--%>
    <%--$('#btnPrint').attr("disabled", false);--%>
    <%--} else {--%>
    <%--$('#btnPrint').attr("disabled", false);--%>
    <%--}--%>
    <%--} else {--%>
    <%--$('#tocord').val(0);--%>
    <%--$('#btnPrint').attr("disabled", true);--%>
    <%--$('#assetToCodeUserId').addClass('is-invalid');--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    jQuery.fn.putCursorAtEnd = function () {
        return this.each(function () {
            var $el = $(this),
                el = this;
            if (!$el.is(":focus")) {
                $el.focus();
            }
            if (el.setSelectionRange) {
                var len = $el.val().length * 2;
                setTimeout(function () {
                    el.setSelectionRange(len, len);
                }, 1);
            } else {
                $el.val($el.val());
            }
            this.scrollTop = 999999;
        });
    };


    function checkAssetCodeValidFrom() {
        var des = $('input[name=getName1]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    $('#btnPrint').attr("disabled", false);
                    $('#assetCodeFrom').css('border-color', '#aaaaaa');

                } else {
                    $('#btnPrint').attr("disabled", true);
                    $('#assetCodeFrom').css('border-color', 'red');
                }

            }

        });
    }

    function checkAssetCodeValidTo() {
        var des = $('input[name=getName2]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    // $('#btnPrint').attr("disabled", false);
                    ('#btnPrint').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');

                } else {
                    $('#btnPrint').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                }

            }
        });
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

    $("#assetCodeFrom").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("fromcord").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    $("#assetCodeTo").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("tocord").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    function isEmptyFrom() {

        if (document.getElementById("assetCodeFrom").value == '') {
            $('#assetCodeFrom').css('border-color', '#aaaaaa');

        }
    }

    function isEmptyTo() {

        if (document.getElementById("assetCodeTo").value == '') {
            $('#assetCodeTo').css('border-color', '#aaaaaa');

        }
    }
    $(function () {
        $(".datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
        }));

    });
</script>