<%--Modification: Modification done for asset code loading function by Dilusha on 2019/06/18--%>

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
        <legend>Computer</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="computerForm"
                  action="${root}/maintenance/saveComputer" method="post">

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-8">

                                <datalist id="divAssetList">
                                </datalist>

                                <%--<div id="listasset">--%>
                                    <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                           <%--onkeyup="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                           <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.."--%>
                                           <%--class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<input id="assetCodeId" name="assetId" hidden>--%>

                                <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                       class="form-control" data-maxitems="100"
                                       placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                <input id="getId" name="assetId" hidden>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Operating System</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="50" required style="width: 100%" id="operatingSystemId"
                                       name="operatingSystem"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Computer Type</div>
                            <div class="col-md-8">

                                <select maxlength="20" required style="width: 100%" id="computerTypeId"
                                        name="computerType">
                                    <option value="0">---SELECT---</option>
                                    <option value="1">Desktop</option>
                                    <option value="2">Laptop</option>
                                    <option value="3">Notebook</option>
                                    <option value="4">Palmtop</option>
                                    <option value="5">PDAs</option>
                                    <option value="6">Digital Diary</option>
                                    <option value="7">Other</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Battery</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="50" required style="width: 100%" id="batteryId"
                                       name="battery"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Brand</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="50" required style="width: 100%" id="brandId"
                                       name="brand"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Buyer Name</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="50" required style="width: 100%" id="buyerId"
                                       name="buyer"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Device Name</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="50" required style="width: 100%" id="deviceNameId"
                                       name="deviceName"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Buyer Address</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="100" required style="width: 100%" id="buyerAddressId"
                                       name="buyerAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Length</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="lengthId"
                                       name="length"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Buyer Telephone No.</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="20" required style="width: 100%" id="buyerTelId"
                                       name="buyerTel"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Width</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="widthId"
                                       name="width"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Colour</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="20" required style="width: 100%" id="computerColorId"
                                       name="computerColor"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Height</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="heightId"
                                       name="height"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Processor</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="20" required style="width: 100%" id="processorId"
                                       name="processor"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Weight</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="weightId"
                                       name="weight"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Adaptor</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="6" required style="width: 100%" id="adapterId"
                                       name="adapter"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">RAM Size</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="ramSizeId"
                                       name="ramSize"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Warranty</div>
                            <div class="col-md-7">
                                <select id="isWarrantyId" required
                                        name="isWarranty"
                                        style="width: 100%">
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Device ID</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="50" required style="width: 100%" id="deviceId"
                                       name="deviceId"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Insurance</div>
                            <div class="col-md-7">
                                <select id="isInsuranceId" required
                                        name="isInsurance" style="width: 100%">
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Product ID</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="50" required style="width: 100%" id="productId"
                                       name="productId"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">System Type</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="50" required style="width: 100%" id="systemTypeId"
                                       name="systemType"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Monitor size</div>
                            <div class="col-md-8">
                                <input type="text" maxlength="6" required style="width: 100%" id="monitorId"
                                       name="monitor"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">HDD</div>
                            <div class="col-md-7">
                                <input type="text" maxlength="6" required style="width: 100%" id="hardDiskCapacityId"
                                       name="hardDiskCapacity"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-6">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-5"></div>
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-6" style="margin-top: 3px">
                                        <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                           href="${root}/maintenance/computer">New</a>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary" id="btnSave"
                                                style="width: 100%; font-size: 14px">Save
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-1"></div>

                </div>
                <input type="hidden" name="computerId" value="0">
            </form>
        </div>
    </div>

</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="update" value="${update}"/>

<script type="text/javascript">

    createJsonComputerObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);
    $('#btnSave').attr("disabled", true);

    //
    // $(function () {
    //     $('.date').datepicker({
    //         format: 'yyyy/mm/dd',
    //         autoclose: true,
    //         endDate: '+0d'
    //     });
    // });


    $(function () {
        $("#datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
            yearRange:'c-200:c+200',
        }));

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

    function loadComputerDetailsByAsset() {
        var assetId = $("#getId").val();

        $.ajax({
            url: '${root}/maintenance/loadComputerDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=computerId]").val(data.computerId);
                    $("input[name=operatingSystem]").val(data.operatingSystem);
                    $("input[name=computerType]").val(data.computerType);
                    document.getElementById("computerTypeId").selectedIndex = data.computerType;
                    $("input[name=battery]").val(data.battery);
                    $("input[name=brand]").val(data.brand);
                    $("input[name=buyer]").val(data.buyer);
                    $("input[name=deviceName]").val(data.deviceName);
                    $("input[name=buyerAddress]").val(data.buyerAddress);
                    $("input[name=length]").val(data.length);
                    $("input[name=buyerTel]").val(data.buyerTel);
                    $("input[name=width]").val(data.width);
                    $("input[name=height]").val(data.height);
                    $("input[name=processor]").val(data.processor);
                    $("input[name=weight]").val(data.weight);
                    $("input[name=adapter]").val(data.adapter);
                    $("input[name=ramSize]").val(data.ramSize);
                    document.getElementById("isWarrantyId").selectedIndex = data.isWarranty;
                    $("input[name=deviceId]").val(data.deviceId);
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;
                    $("input[name=productId]").val(data.productId);
                    $("input[name=systemType]").val(data.systemType);
                    $("input[name=computerColor]").val(data.computerColor);
                    $("input[name=monitor]").val(data.monitor);
                    $("input[name=hardDiskCapacity]").val(data.hardDiskCapacity);
                    $("#btnSave").html('Update');
                } else {
                    $("input[name=computerId]").val("0");
                    $("input[name=operatingSystem]").val("");
                    document.getElementById("computerTypeId").selectedIndex = 0;
                    $("input[name=battery]").val("");
                    $("input[name=brand]").val("");
                    $("input[name=buyer]").val("");
                    $("input[name=deviceName]").val("");
                    $("input[name=buyerAddress]").val("");
                    $("input[name=length]").val("");
                    $("input[name=buyerTel]").val("");
                    $("input[name=width]").val("");
                    $("input[name=height]").val("");
                    $("input[name=processor]").val("");
                    $("input[name=weight]").val("");
                    $("input[name=adapter]").val("");
                    $("input[name=ramSize]").val("");
                    document.getElementById("isInsuranceId").selectedIndex = 0;
                    $("input[name=deviceId]").val("");
                    document.getElementById("isWarrantyId").selectedIndex = 0;
                    $("input[name=productId]").val("");
                    $("input[name=systemType]").val("");
                    $("input[name=computerColor]").val("");
                    $("input[name=monitor]").val("");
                    $("input[name=hardDiskCapacity]").val("");
                    $("#btnSave").html('Save');

                }
            }
        });

    }

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
                    window.location.href = "./computer";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./computer";
                })
            }
        }

    });


    <%--function loadAssetByType() {--%>
    <%--var type = 4;--%>
    <%--$.ajax({--%>
    <%--url: '${root}/maintenance/loadAssetByType/' + type,--%>
    <%--success: function (data) {--%>
    <%--for (var i = 0; i < data.length; i++) {--%>
    <%--$("#assetCode").append(--%>
    <%--"<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2]--%>
    <%--+ "</option>");--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
            <%--var type = 4;--%>


            <%--if (assetCodeIdval == '') {--%>
                <%--assetCodeIdval = 0;--%>
            <%--}--%>

            <%--$.ajax({--%>
                <%--url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval + '/' + type,--%>

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

    <%--function checkAssetCodeValid() {--%>
        <%--var assetCode = $('input[name=assetCodeUser]').val();--%>

        <%--$.ajax({--%>
            <%--url: '${root}/data/checkAssetCodeValid/' + assetCode,--%>
            <%--success: function (data) {--%>
                <%--if (data != 0) {--%>
                    <%--$('#assetCodeUserId').removeClass('is-invalid');--%>
                    <%--$('#assetCodeId').val(data);--%>
                    <%--$('#btnSave').attr("disabled", false);--%>
                    <%--loadComputerDetailsByAsset();--%>
                <%--} else {--%>
                    <%--$('#assetCodeId').val(0);--%>
                    <%--$('#btnSave').attr("disabled", true);--%>
                    <%--$('#assetCodeUserId').addClass('is-invalid'); // Giving a red border when invalid asset Code--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>

    <%--}--%>



    var jsonArray = [];

    function createJsonComputerObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 4;

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

    function checkAssetCodeValid() {
        var des = $('input[name=getName]').val();
        var assetCode = des.substring(0, ASSET_CODE_LENGTH);

        $.ajax({
            url: '${root}/data/checkAssetCodeValid/' + assetCode,
            success: function (data) {
                if (data != 0) {

                    $('#btnSave').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    loadComputerDetailsByAsset();
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