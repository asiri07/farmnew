<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
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
<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Leased Asset</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="leaseAssetForm" action="${root}/maintenance/saveLeaseAsset" method="post">

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">
                                <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text"
                                       autocomplete="off"
                                       class="form-control"
                                       placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()"
                                       onchange="clearFields()">
                                <input id="getId" name="assetId" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-7">
                                <select id="transactionSelectId" class="chosen" style="width: 100%"
                                        onchange="loadLeaseAssetDetails()"
                                        name="transactionSelect">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Lease Type</div>
                            <div class="col-md-8">
                                <select name="leaseType" id="leaseTypeId" style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <option value="1">Lease</option>
                                    <option value="2">Higher Purchase</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Period From</div>
                            <div class="col-md-7">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control"
                                           name="leasePeriodFrom" id="leasePeriodFromId" style="height:92%"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px"> Agreement No.</div>
                            <div class="col-md-8">
                                <input type="text" name="leaseAgreNo" maxlength="11" id="leaseAgreNoId" style="width: 100%" />
                            </div>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Period To</div>
                            <div class="col-md-7">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control" name="leasePremiumTo"
                                           style="height: 92%"
                                           id="leasePremiumToId"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Leased Value</div>
                            <div class="col-md-8">
                                <input type="text" name="displayLeaseTot" id="leaseTotId"
                                       required maxlength="30"
                                       onkeypress="return isNumberAndDecimalOnly(event)"
                                       onblur="return addComma(id)"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Lease Company</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="leaseCompanyId" maxlength="200"
                                       name="leaseCompany"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Premium</div>
                            <div class="col-md-8">
                                <input type="text" name="displayLeasePremium" maxlength="30" id="leasePremiumId"
                                       required
                                       onkeypress="return isNumberAndDecimalOnly(event)" onblur="return addComma(id)"
                                       style="width: 100%"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Contact person</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="contactPersonId" maxlength="50"
                                       name="contactPerson"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Premium Date</div>
                            <div class="col-md-8">
                                <select type="text" name="leasePremiumDate" id="leasePremiumDateId" required
                                        style="width: 100%">
                                    <option value="0">---SELECT---</option>
                                    <option value="01st"> 1st</option>
                                    <option value="02nd"> 2nd</option>
                                    <option value="03rd"> 3rd</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Address</div>
                            <div class="col-md-7">
                                    <textarea name="leaseAddress" id="leaseAddressId" maxlength="200"
                                              style="width: 100%"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">No. of Installment</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="leaseNoPremiumId" onkeypress="return isNumberOnly(event)"
                                       name="leaseNoPremium"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Telephone No.</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="leaseTelephoneNoId"
                                       maxlength="20" name="leaseTelephoneNo"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-1"></div>

                </div>
<div class="row">&nbsp;</div>
                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="margin: 0;padding-top: 10px"></div>
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-6">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top:3px;"
                                           href="${root}/maintenance/leaseAsset">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" id="btnSave" class="btn btn-primary"
                                                style="width: 100%; font-size: 14px">Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <input type="hidden" name="leaseID" id="leaseID" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"
                       style="width: 100%"/>
            </form>
        </div>
    </div>
</div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>


<script type="text/javascript">

    createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);
    loadPremiumDate();


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
                    window.location.href = "./leaseAsset";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000,
                }).then(function () {
                    window.location.href = "./leaseAsset";
                })
            }
        }
    });


    function loadTransactionNoList() {

        var assetId = $("#getId").val();

        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadLeaseAssetNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i].leaseID + "'>" + data[i].transactionNo
                        + "</option>");
                }
            }
        });
    }

    function loadLeaseAssetDetails() {
        var leaseId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/maintenance/loadLeaseAssetDetailsByLeaseId/' + leaseId,
            success: function (data) {
                if (data != "") {
                   // alert(data.leaseTot);
                    //var val1=addCommaForNumber(data.leaseTot);
                    //alert(val1);
                    // var val2 = addCommaForNumber(data.leasePremium);
                    $("input[name=leaseID]").val(data.leaseID);
                    $("input[name=transactionNo]").val(data.transactionNo);
                    $("input[name=leaseAgreNo]").val(data.leaseAgreNo);
                    $("input[name=displayLeaseTot]").val(data.leaseTot);
                    $("input[name=leasePeriodFrom]").val(dateFormator(data.leasePeriodFrom));
                    $("input[name=leasePremiumTo]").val(dateFormator(data.leasePremiumTo));
                    $("input[name=leaseNoPremium]").val(data.leaseNoPremium)
                    // $("input[name=leasePremiumDate]").val(data.leasePremiumDate);
                    $("input[name=leaseCompany]").val(data.leaseCompany);
                    $("input[name=contactPerson]").val(data.contactPerson);
                    $("textarea[name=leaseAddress]").val(data.leaseAddress);
                    $("input[name=leaseTelephoneNo]").val(data.leaseTelephoneNo);
                    $("input[name=displayLeasePremium]").val(data.leasePremium);
                    document.getElementById("leaseTypeId").selectedIndex = data.leaseType;
                    document.getElementById("leasePremiumDateId").value = data.leasePremiumDate;
                    addComma('leasePremiumId');
                    addComma('leaseTotId');
                    $("#btnSave").html('Update');
                }
            }
        });
    }


    function clearFields() {
        $("input[name=leaseID]").val("0");
        $("input[name=transactionNo]").val("");
        $("input[name=leaseAgreNo]").val("");
        $("input[name=leasePeriodFrom]").val("");
        $("input[name=leasePremiumTo]").val("");
        $("input[name=leaseNoPremium]").val("");
        // $("input[name=leasePremiumDate]").val("");
        $("input[name=leaseCompany]").val("");
        $("input[name=contactPerson]").val("");
        $("textarea[name=leaseAddress]").val("");
        $("input[name=leaseTelephoneNo]").val("");
        $("input[name=displayLeasePremium]").val("");
        $("input[name=displayLeaseTot]").val("");
        document.getElementById("leaseTypeId").selectedIndex = 0;
        document.getElementById("leasePremiumDateId").selectedIndex = 0;
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


    function loadPremiumDate() {
        for (var i = 4; i < 32; i++) {

            if (i == 31) {
                $("#leasePremiumDateId").append(
                    "<option value = '" + i + "st'>" + i + "st"
                    + "</option>");
            }
            else if(i<=9){
                $("#leasePremiumDateId").append(
                    "<option value = '" +"0"+ i + "th'>" + i + "th"
                    + "</option>");
            }
            else{
                $("#leasePremiumDateId").append(
                    "<option value = '" + i + "th'>" + i + "th"
                    + "</option>");
            }
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
            url: '${root}/maintenance/getAssetByLease/'+ userBranch,
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
