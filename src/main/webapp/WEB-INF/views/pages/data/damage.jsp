<%--
  Created by IntelliJ IDEA.
  User: MsD
  Date: 9/28/2017
  Time: 4:17 AM
  To change this template use File | Settings | File Templates.

  Modification Date     : 3/8/2019:
  User                  : Dilusha Kumari
  Purpose               : Add a method for generating Transaction Number

  Modification Date     : 6/25/2019:
  User                  : Dilusha Kumari
  Purpose               : Modified Asset Code loading method
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
<div class="col-md-12 ">
    <div class="row">
        <div class="col-md-6 page">
            <form name="damageForm" action="${root}/data/saveAssetDamage" method="post">

                <div class="row" style="margin: 0">
                    <legend>Damaged Asset</legend>

                </div>


                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                    <div class="col-md-8" id="divAssetTo">
                        <%--<div class="" style="width:100%">--%>

                        <%--OPTION 1--%>
                        <%--<select required id="assetCodeId" style="width: 101%;" class="chosen" name="asset"--%>
                        <%--onchange="loadTransactionNoList()">--%>
                        <%--<option value="">---SELECT---</option>--%>
                        <%--<c:forEach var="asset" items="${assets}">--%>
                        <%--<option value="${asset.asId}">${asset.asCode}-${asset.asDes}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--OPTION 1--%>

                        <%--OPTION 4--%>
                        <%--<datalist id="divAssetList">--%>
                        <%--</datalist>--%>

                        <%--<div id="listasset" >--%>
                        <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                        <%--onkeyup="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                        <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control"/>--%>
                        <%--</div>--%>
                        <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text"
                               autocomplete="off"
                               class="form-control" data-maxitems="100"
                               placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                        <input id="getId" name="asset" hidden>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="margin-left: -1px;margin-top: 10px">Transaction No.</div>
                    <div class="col-md-8">
                        <select id="transactionSelectId" style="width: 100%"
                                onchange="loadDamageDetails()"
                                name="transactionSelect">
                            <option value="">---SELECT---</option>
                        </select>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Date</div>
                    <div class="col-md-8">
                        <div class='input-group date'>
                            <input type='text' class="form-control" style="height:35px" onchange="checkDisposalDate()"
                                   required="required" name="date" id="dpDateId"/>
                            <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Value</div>
                    <div class="col-md-8">
                        <input type="text" name="dmgCost" id="dmgCost" maxlength="16" required
                               onkeypress="return isNumberAndDecimalOnly(event)"
                               onblur="addComma(id)"
                               style="width: 100%"/>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 30px">Description</div>
                    <div class="col-md-8">
                        <textarea name="dmgDes" required maxlength="50" style="width: 100%"></textarea>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-8">
                        <div class="row" style="padding-top: 10px">

                            <div class="col-md-6">
                                <a class="btn btn-primary"
                                   style="width: 140px;height: 30px; font-size: 14px; margin-top:3px;"
                                   href="${root}/data/damage">New</a>
                            </div>
                            <div class="col-md-6">
                                <button onclick="CommaRemove('dmgCost');" id="btnSave" class="btn btn-primary"
                                        style="width: 140px;height: 30px; font-size: 14px;">Save
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
                <input type="hidden" name="dmgAsId" id="dmgAsId" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"/>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="typeselect" value="0"/>


<script type="text/javascript">

    createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            // endDate: '+0d'
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

    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Success!!! Transaction No. : ";
        } else {
            message = "Update success!!! ";
        }

        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message + transactionNo,
                    type: 'success'
                }).then(function () {
                    window.location.href = "./damage";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 5000
                }).then(function () {
                    window.location.href = "./damage";
                })
            }
        }
    });

    function checkDisposalDate() {
        var dat = $("input[name=date]").val();
        var assetId = $("#getId").val();

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


    function loadTransactionNoList() {
        var assetId = $("#getId").val();

        $("#transactionSelectId").find("option:not(:first)").remove();
        var userBranch = '<%= session.getAttribute("userBranch") %>';
        $.ajax({
            url: '${root}/data/loadDamageNoList/' + assetId + '/' + userBranch,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i][0] + "'>" + data[i][1]
                        + "</option>");
                }
                $("input[name=dmgAsId]").val("0");
                $("input[name=date]").val("");
                $("input[name=dmgCost]").val("");
                $("textarea[name=dmgDes]").val("");
            }
        });
    }

    function loadDamageDetails() {
        var damageId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/data/loadDamageDetailsByDamageId/' + damageId,
            success: function (data) {
                if (data != "") {
                    $("input[name=dmgAsId]").val(data[0][0]);
                    $("input[name=transactionNo]").val(data[0][1]);
                    $("input[name=date]").val(dateFormators(data[0][2]));
                    $("input[name=dmgCost]").val(data[0][3]);
                    $("textarea[name=dmgDes]").val(data[0][4]);
                    addComma("dmgCost");
                    $("#btnSave").html('Update');
                }
            }
        });
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
    <%--'               onkeypress="loadAssetByType()"' +--%>
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