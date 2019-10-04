<%--
  Created by IntelliJ IDEA.
  User: Dilusha Kumari
  Date: 1/17/2019
  Time: 10:45 AM
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
<div class="col-md-6 page">
    <div class="row" style="margin: 0">
        <legend> Modification</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="modificationForm" action="${root}/maintenance/saveModification" method="post">

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                    <div class="col-md-8">
                        <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                               class="form-control"
                               placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()"  onchange="clearFields(), loadTransactionNoList()">
                        <input id="getId" name="assetId" hidden>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Transaction No.</div>
                    <div class="col-md-8">
                        <select id="transactionSelectId" class="chosen" style="width: 100%"
                                onchange="loadModificationDetails()"
                                name="transactionSelect">
                            <option value="">---SELECT---</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Type</div>
                    <div class="col-md-8">
                        <select class="chosen" id="modiTypeId"
                                name="modiType" style="width: 100%">
                            <option value="0">---SELECT---</option>
                            <option value="1">Enhance</option>
                            <option value="2">Improvement</option>
                            <option value="3">Demolish</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Date</div>
                    <div class="col-md-8">
                    <div class='input-group date' style="width: 102%">
                    <input type='text' required class="form-control" style="height: 93%" name="modiDate" id="modiDateId"
                    onchange="checkregidterdDate($('input[name=modiDate]').val(),$('#assetCode').find('option:selected').val(),'modiDateId')">
                    <span class="input-group-addon">
                    <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                    <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                    <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                    </span>
                    </div>
                    </div>



    </div>

    <div class="row">
        <div class="col-md-4" style="padding-top: 10px"> Work</div>
        <div class="col-md-8">
            <input type="text" required style="width: 100%" id="workId" name="modiWork" maxlength="200"
                   required/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4" style="padding-top: 10px">Currency Type</div>
        <div class="col-md-8">
            <select id="currencyTypeId" required class="select_style"
                    name="currencyType" style="width: 100%"/>
            <option value="0">---SELECT---</option>
            <c:forEach items="${currencyTypes}" var="currencyType">
                <option value="${currencyType.currencyId}">${currencyType.currencyCode}</option>
            </c:forEach>
            </select>
        </div>
    </div>


    <div class="row">
        <div class="col-md-4" style="padding-top: 10px">Cost</div>
        <div class="col-md-8">
            <input type="text" required style="width: 100%" id="costId" name="costDisplay" maxlength="14"
                   onblur="return addComma(id)"
                   onkeypress="return isNumberAndDecimalOnly(event)" required/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4" style="padding-top: 10px">Reason</div>
        <div class="col-md-8">
            <input type="text" required style="width: 100%" id="reasonId" name="modiReason" maxlength="200"
                   required/>
        </div>
    </div>

    <div class="row">&nbsp</div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-6" style="margin-top: 3px">
                    <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                       href="${root}/maintenance/modification">New</a>
                </div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary" style="width: 100%; font-size: 14px"
                            id="btnSave">Save
                    </button>
                </div>
            </div>
        </div>

    </div>
    <input type="hidden" name="modificationId" value="${modification.modificationId}">
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
                    window.location.href = "./modification";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000,
                }).then(function () {
                    window.location.href = "./modification";
                })
            }
        }
    });


    function loadTransactionNoList() {
        var assetId = $("#getId").val();
        $("#transactionSelectId").find("option:not(:first)").remove();
        $.ajax({
            url: '${root}/maintenance/loadModificationNoList/' + assetId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#transactionSelectId").append(
                        "<option value = '" + data[i].modificationId + "'>" + data[i].transactionNo
                        + "</option>");
                }
            }
        });
    }

    function loadModificationDetails() {
        var modificationId = $("#transactionSelectId").find("option:selected").val();

        $.ajax({
            url: '${root}/maintenance/loadModificationDetailsByModificationId/' + modificationId,
            success: function (data) {
                if (data != "") {
                    var value = addCommaForNumber(data.modiCost);
                    $("input[name=modificationId]").val(data.modificationId);
                    $("input[name=transactionNo]").val(data.transactionNo);
                    document.getElementById("modiTypeId").selectedIndex = data.modiType;
                    $("input[name=modiDate]").val(dateFormators(data.modiDate));
                    $("input[name=modiWork]").val(data.modiWork);
                    $("input[name=costDisplay]").val(value);
                    $("input[name=modiReason]").val(data.modiReason);
                    document.getElementById("currencyTypeId").selectedIndex = data.currencyType;
                    $("#btnSave").html('Update');
                }
            }
        });
    }


    function clearFields() {
        $("input[name=modificationId]").val("0");
        $("input[name=transactionNo]").val("");
        document.getElementById("modiTypeId").selectedIndex = 0;
        $("input[name=modiDate]").val("");
        $("input[name=modiWork]").val("")
        $("input[name=costDisplay]").val("");
        $("input[name=modiReason]").val("");
        document.getElementById("currencyTypeId").selectedIndex = 0;
        $("#btnSave").html('Save');
    }

    function checkregidterdDate(dat, assetId, id) {
        var idName = id.valueOf();
        var concatIdName = "#" + idName;

        var type = assetId;
        if (dat != "" && assetId > 0) {
            var date = formatDate(dat);
            $.ajax({
                url: '${root}/maintenance/getAssetData/' + type,
                success: function (data) {
                    if (date < formatDate(data)) {
                        swal({
                            title: 'Warning',
                            text: 'Please select Date after the Registered Date!',
                            type: 'warning',
                            timer: 4000
                        })
                        $(concatIdName).val("");
                    }
                }
            });
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