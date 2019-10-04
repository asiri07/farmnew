<%--Modification Date     : 3/8/2019:--%>
<%--User                  : Dilusha Kumari--%>
<%--Purpose               : Add a method for generating Transaction Number--%>

<%--Modification Date     : 6/25/2019:--%>
<%--User                  : Dilusha Kumari--%>
<%--Purpose               : Modified Asset Code loading method--%>


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
<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Asset Disposal</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="disposalForm"
                  action="${root}/data/saveAssetDisposal" method="post">

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8" id="divAsset">
                                <%--<select class="select_style chosen" id="assetCodeId" required--%>
                                        <%--name="asset.asId"--%>
                                        <%--onchange="getAssetAllocation(), clearDisposalFields()" style="width: 100%">--%>
                                    <%--<option value="">---SELECT---</option>--%>
                                    <%--<c:forEach var="asset" items="${assets}">--%>
                                        <%--<option value="${asset.asId}">${asset.asCode} - ${asset.asDes}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>

                                    <%--<datalist id="divAssetList">--%>
                                    <%--</datalist>--%>

                                    <%--<div id="listasset">--%>
                                        <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                               <%--onkeypress="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                               <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control"/>--%>
                                    <%--</div>--%>
                                    <%--<input id="assetCodeId" name="asset" hidden>--%>

                                    <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control" placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                    <input id="getId" name="asset" hidden>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                            <div class="col-md-8">
                                <select id="transactionSelectId" style="width: 100%"
                                        onchange="loadDisposalDetails()"
                                        name="transactionSelect">
                                    <option value="">---SELECT---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Date</div>
                            <div class="col-md-8">
                                <div class='input-group date'>
                                    <input type='text'  class="form-control" style="height:35px" onchange="checkDisposalDate()"
                                           required="required" name="dpDate" id="dpDateId"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Currency Type</div>
                            <div class="col-md-8"><select id="currencyId" required class="select_style"
                                                          name="currencyType" style="width: 100%">
                                <option value="0">---SELECT---</option>
                                <c:forEach items="${currencyTypes}" var="currencyType">
                                    <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
                                </c:forEach>
                            </select>
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"  style="padding-top: 10px">Company</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="companyID" name="companyID" readonly/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Disposal Value</div>
                            <div class="col-md-8">
                                <input type="text" onkeypress="return isNumberAndDecimalOnly(event)" maxlength="16" required
                                       style="width: 100%" onblur="return addComma(id)"
                                       name="currencyValue" id="currencyValue"/>
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"style="padding-top: 10px">Department</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="departmentID" name="departmentID" readonly/>
                            </div>

                        </div>
                    </div>

                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Reason</div>
                            <div class="col-md-8">
                                <select required style="width: 100%" id="dpReasonId" name="dpReason">
                                    <option value="">---SELECT---</option>
                                    <c:forEach items="${reasons}" var="reasons">
                                        <option value="${reasons.reason}">${reasons.reason}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-2"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4"style="padding-top: 10px">Section</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="sectionID" name="" readonly/>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Remarks</div>
                            <div class="col-md-8">
                                <textarea name="dpDes" maxlength="50" col="5" rows="2" style="width: 100%"></textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Location</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="locationID" name="" readonly/>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-5">

                    </div>

                </div>

                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-5">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a class="btn btn-primary"
                                   style="margin-top:4px;width: 140px;height: 30px; font-size: 14px"
                                   href="${root}/data/disposal">New</a>
                            </div>
                            <div class="col-md-4" style="margin-top: 1px">
                                <button class="btn btn-primary" onclick="CommaRemove('currencyValue');" id="btnSave"
                                        style="width: 140px;height: 30px; font-size: 14px">
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>
                <input type="hidden" name="dpId" id="dpId" value="0">
                <input type="hidden" name="transactionNo" id="transactionNo" value="${transactionNo}"/>
            </form>
        </div>
    </div>
    <div class="row">&nbsp</div>
    <div class="row">&nbsp</div>
    <%--<div class="row">--%>
    <%--<div class="col-md-12">--%>
    <%--<table id="dataTable" class="display compact" width="100%" cellspacing="0">--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th>Asset Code</th>--%>
    <%--<th>Date</th>--%>
    <%--<th>Disposal Value</th>--%>
    <%--<th>Reason</th>--%>

    <%--</tr>--%>
    <%--</thead>--%>

    <%--<tbody>--%>
    <%--<c:forEach var="disposal" items="${disposals}">--%>
    <%--<tr>--%>
    <%--<td>${disposal.asset.asCode}</td>--%>
    <%--<td><fmt:formatDate pattern="dd-MM-yyyy" value="${disposal.dpDate}"/></td>--%>
    <%--<td>${disposal.dp_value}</td>--%>
    <%--<td>${disposal.dpReason}</td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</tbody>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</div>--%>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isRate" value="${isRate}"/>

<script type="text/javascript">


    createJsonObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

    // loadAssetByType();
    loadTransactionNoList();


    // $(".chosen").chosen();
    // $('.tabgroup > div').hide();
    // $('.tabgroup > div:first-of-type').show();
    // $('.tabs a').click(function (e) {
    //     e.preventDefault();
    //     var $this = $(this),
    //         tabgroup = '#' + $this.parents('.tabs').data('tabgroup'),
    //         others = $this.closest('li').siblings().children('a'),
    //         target = $this.attr('href');
    //     others.removeClass('active');
    //     $this.addClass('active');
    //     $(tabgroup).children('div').hide();
    //     $(target).show();
    // });

    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
            endDate: '+0d'
        });
    });

    // $(function () {
    //     $("#dpDateId").datepicker(({
    //         dateFormat: 'yy/mm/dd',
    //         changeMonth: true,
    //         changeYear: true,
    //         showOtherMonths: true,
    //         yearRange:'c-200:c+200',
    //     }));
    //
    // });

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
        var isRate = $("#isRate").val();
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var transactionNo = $("#transactionNo").val();
        var message = "";

        if (transactionNo != "") {
            message = "Success!!! Transaction No. : ";
        } else {
            message = "Update success!!! ";
        }
        if (pageType == 1) { //IF pageType is saved mode
            if (status == 'true') { // save complete or not
                swal({
                    title: message + transactionNo,
                    type: 'success',
                }).then(function () {
                    window.location.href = "./disposal";
                })
            } else if (isRate == 'false') { //currency rate not in the date
                swal({
                    title: 'Error',
                    text: 'Please Update Currency Rate in Disposal Date !!',
                    type: 'error',
                    timer: 1000
                })
            } else { // save not complete
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./disposal";
                })
            }
        }
    });

    function getAssetAllocation() {
        // var assetId = $("#assetCodeId").find("option:selected").val();
        // var assetId = $("#assetCodeId").val();
        var assetId = $("#getId").val();

        if (assetId > 0) {
            $.ajax({
                url: '${root}/data/getAssetAlocation/' + assetId,
                success: function (data) {
                    if (data != "") {
                        var subCat = data.split("-");
                        $("#companyID").val("");
                        $("#departmentID").val("");
                        $("#sectionID").val("");
                        $("#locationID").val("");
                        $("#companyID").val(subCat[0].toUpperCase());
                        $("#departmentID").val(subCat[1].toUpperCase());
                        $("#sectionID").val(subCat[2].toUpperCase());
                        $("#locationID").val(subCat[3].toUpperCase());
                    }
                }
            });
        }
    }

    function clearDisposalFields() {
        $("#btnSave").html('Save');
        $("#btnSave").prop('disabled', false);
        $("input[name=dpId]").val("0");
        $("input[name=transactionNo]").val("0");
        $("textarea[name=dpDes]").val("");
        $("input[name=currencyValue]").val("");
        $("#currencyId").val("");
        document.getElementById("dpReasonId").selectedIndex = 0;
        // addComma("currencyValue");
        $("#dpReasonId").value = "";
        $("#dpDateId").val("");
        document.getElementById("currencyId").selectedIndex = 0;
        document.getElementById("transactionSelectId").selectedIndex = 0;
    }

    function checkDisposalDate() {
        var dat = $("input[name=dpDate]").val();
        // var assetId = $("#assetCodeId").find("option:selected").val();
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
                                msg = "Please change Disposal date";
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
                            timer: 2000
                        })
                        $("input[name=dpDate]").val("");
                    }
                }
            });
        }
    }


    function loadTransactionNoList() {
        // var assetId = $("#assetCodeId").find("option:selected").val();
        // var assetId = $("#assetCodeId").val();
        var assetId = $("#getId").val();
        $("#transactionSelectId").find("option:not(:first)").remove();
        var userBranch = '<%= session.getAttribute("userBranch") %>';

        $.ajax({
            url: '${root}/data/loadDisposeNoList/' + userBranch,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i][0] + "'>" + data[i][1]
                        + "</option>");
                }
            }
        });
    }

    function loadDisposalDetails() {
        var disposalTrNo = $("#transactionSelectId").find("option:selected").val();
        $.ajax({
            url: '${root}/data/loadDisposalByTRNo/' + disposalTrNo,
            success: function (data) {
                if (data != "") {
                    $("#btnSave").html('Update');
                    $("#btnSave").prop('disabled', false);
                    $("input[name=dpId]").val(data[0][0]);
                    $("input[name=transactionNo]").val(data[0][1]);
                    $("textarea[name=dpDes]").val(data[0][2]);
                    $("input[name=currencyValue]").val(data[0][3]);
                    document.getElementById("currencyId").value = data[0][4];
                    addComma("currencyValue");
                    document.getElementById('dpReasonId').value = data[0][5];
                    $("#dpDateId").val(dateFormators(data[0][6]));
                    var value= data[0][9] + " - " + data[0][8];
                    var assetId=data[0][7];
                    $("#assetCodeTo").val(value);
                    $("#getId").val(assetId);

                    getAssetAllocation();
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

    function checkAssetCodeValid() {
        // var assetCode = $('input[name=assetCodeUser]').val();

        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {
                    loadTransactionNoList();
                    // $('#assetCodeUserId').removeClass('is-invalid');
                    // $('#assetCodeId').val(data);
                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    getAssetAllocation(); clearDisposalFields();
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

    })

</script>