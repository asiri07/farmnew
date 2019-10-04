<%--Created By      :   Dilusha Kumari--%>
<%--Created Date    :   2019/01/15--%>
<%--Modification    :   Modification done for asset code loading function by Dilusha on 2019/06/18--%>

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
        <legend>Furniture</legend>
    </div>
    <%--<div class="row">&nbsp;</div>--%>

    <div class="row">
        <div class="col-md-12">
            <form name="furnitureForm" action="${root}/maintenance/saveFurniture" method="post">

                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code From:</div>
                    <div class="col-md-8">

                            <input id="assetCodeFrom" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                   class="form-control" data-maxitems="100"  onblur="checkAssetCodeValidFrom(),isEmptyFrom()"
                                   placeholder="Enter 1st character" required >
                            <input id="fromCodeId" name="assetIdFrom" hidden>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Asset Code To:</div>
                    <div class="col-md-8">
                        <input id="assetCodeTo" name="getName2" style="height:35px;width:100%" type="text"
                               autocomplete="off" required
                               class="form-control" data-maxitems="100"
                               placeholder="Enter 1st character" onblur="checkAssetCodeValidTo(),isEmptyTo(),checkIsSameTransactionNo()">
                        <input id="assetIdTo" name="assetIdTo" hidden>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Warranty</div>
                    <div class="col-md-8">
                        <select id="isWarrantyId" required
                                name="isWarranty"
                                style="width: 100%">
                            <option value="0"> No</option>
                            <option value="1"> Yes</option>
                        </select>
                    </div>
                </div>
                <%--<div class="row">&nbsp;</div>--%>
                <div class="row">
                    <div class="col-md-4" style="padding-top: 10px">Insurance</div>
                    <div class="col-md-8">
                        <select id="isInsuranceId" required
                                name="isInsurnce" style="width: 100%">
                            <option value="0"> No</option>
                            <option value="1"> Yes</option>
                        </select>
                    </div>

                </div>
                <div class="row">&nbsp;</div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-6" style="margin-top: 3px">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                   href="${root}/maintenance/furniture">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit"  id="btnSave" class="btn btn-primary" style="width: 100%; font-size: 14px">Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

        </div>
        <input type="hidden" value="0" name="furnitureId">
        </form>
    </div>
</div>

</div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="update" value="${update}"/>


<script type="text/javascript">

    createJsonObject();
    $('#btnSave').attr("disabled", true);
    document.getElementById('assetCodeFrom').setAttribute('maxlength', ASSET_CODE_LENGTH);
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
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
        var update = $("#update").val();

        if (update == 0) {
            message = "Saved Successfully!!! ";
        } else {
            message = "Updated Successfully!!! ";
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message,
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./furniture";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./furniture";
                })
            }
        }

    });

    function loadFurnitureDetails() {

        var assetId = $("#fromCodeId").val();
        $.ajax({
            url: '${root}/maintenance/loadFurnitureDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=furnitureId]").val(data.furnitureId);
                    $("input[name=assetIdFrom]").val(data.assetId);
                    document.getElementById("isWarrantyId").selectedIndex = data.isWarranty;
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurnce;
                } else {
                    $("input[name=furnitureId]").val("0");
                }
            }
        });
    }

    function checkIsSameTransactionNo() {

        var assetIdFrom = $("#fromCodeId").val();
        var assetIdTo = $("#assetIdTo").val();

        $.ajax({
            url: '${root}/data/loadTransactionNo/' + assetIdFrom+ '/' + assetIdTo,
            success: function (data) {

                if (data == 0) {
                    swal({
                        title: 'warning',
                        text: 'Select Asset Code To in Same Transaction Number',
                        type: 'warning',
                        timer: 5000
                    });
                    $("#assetCodeTo").val("");
                    $('#btnSave').attr("disabled", true);


                }
            }
        });
    }




    function checkAssetCodeValidFrom() {
        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    $('#assetCodeFrom').css('border-color', '#aaaaaa');
                    loadFurnitureDetails();

                } else {
                    $('#btnSave').attr("disabled", true);
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

                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');

                } else {
                    $('#btnSave').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                }
            }
        });
    }


    var jsonArray = [];

    function createJsonObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 6;

        $.ajax({
            url: '${root}/data/loadAssetByTypes/' + userBranch + '/' + type,
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

            document.getElementById("assetIdTo").value = ui.item.id;

        },
        delay: 0,
        minLength: 1,
        max: 100,

    });

    $("#assetCodeFrom").autocomplete({
        source: function (request, response) {
            var results = $.ui.autocomplete.filter(jsonArray, request.term);

            response(results.slice(0, 100));
        },

        select: function (event, ui) {

            document.getElementById("fromCodeId").value = ui.item.id;

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

</script>