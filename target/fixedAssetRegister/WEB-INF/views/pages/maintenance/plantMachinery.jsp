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
<div class="col-md-12 page">
    <div class="row" style="margin: 0">
        <legend>Plant & Machinery</legend>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form name="plantMachineryForm" action="${root}/maintenance/savePlantMachinery" method="post">

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-7">

                                <%--<select class="chosen" id="assetCodeId" required--%>
                                <%--name="assetId" onchange="loadPlantMachineryDetails()"--%>
                                <%--style="width: 100%">--%>
                                <%--<option value="">---SELECT---</option>--%>
                                <%--<c:forEach var="asset" items="${assets}">--%>
                                <%--<option value="${asset.asId}">${asset.asCode} - ${asset.asDes}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                                <%--<datalist id="divAssetList">--%>
                                <%--</datalist>--%>

                                <%--<div id="listasset">--%>
                                    <%--<input autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"--%>
                                           <%--onkeyup="loadAssetByType()" onblur="checkAssetCodeValid()"--%>
                                           <%--id="assetCodeUserId" name="assetCodeUser" placeholder="Search.."--%>
                                           <%--class="form-control"/>--%>
                                <%--</div>--%>
                                <%--<input id="assetCodeId" name="assetId" hidden>--%>

                                    <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control"
                                           placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                    <input id="getId" name="assetId" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Length</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="6" id="lengthId"
                                       name="length"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Fuel Type</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="20" id="typeFuelId"
                                       name="typeFuel"/>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Width</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="6" id="weidthId"
                                       name="weidth"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Make</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="20" id="makeId" name="make"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Height</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="6" id="heightId"
                                       name="height"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Country of Origin</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="50" id="countryOriginId"
                                       name="countryOrigin"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Warranty</div>
                            <div class="col-md-8">
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
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Model</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="50" id="modelId"
                                       name="model"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Insurance</div>
                            <div class="col-md-8">
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
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Year of Manufacture.</div>
                            <div class="col-md-7">
                                <%--<input type='text' maxlength="6" required style="width: 100%" name="yearManufacture"--%>
                                <%--id="yearManufactureId"/>--%>
                                <input type="number" class="date-own form-control"
                                       onkeypress="return isNumberOnly(event)" required maxlength="4"
                                       onblur="checkRegisterdYear()"
                                       style="width: 100%; height: 92%; font-size: 14px" type="text"
                                       id="yearManufactureId" min="1900" max="2099"
                                       name="yearManufacture">
                                <script type="text/javascript">
                                    $('.date-own').datepicker({
                                        minViewMode: 2,
                                        format: 'yyyy'
                                    });
                                </script>

                                    <%--<input type='text' maxlength="4" required   id="yearManufactureId" style="width: 100%" name="yearManufacture" onkeypress="return isNumberOnly(event)"  onblur="checkRegisterdYear()">--%>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Service Agreement</div>
                            <div class="col-md-8">
                                <select id="isServiceAgreId" required
                                        name="isServiceAgre" style="width: 100%">
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Weight</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="6" id="weightId"
                                       name="weight"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Buyer</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="100" id="bayerId"
                                       name="bayer"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Manufacture</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="100" id="manufactureId"
                                       name="manufacture"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Telephone</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="15" id="telephoneId"
                                       name="telephone"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Address</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" maxlength="100" id="addressId"
                                       name="address"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">

                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6"></div>
                    <div class="col-md-5">
                        <div class="row" style="padding-top: 10px">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top: 3px"
                                   href="${root}/maintenance/plantMachinery">New</a>
                            </div>
                            <div class="col-md-4">
                                <button type="submit" id="btnSave" class="btn btn-primary"
                                        style="width: 100%; font-size: 14px;">Save
                                </button>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-1"></div>

                </div>
                <input type="hidden" id="pageType" value="${pageType}"/>
                <input type="hidden" id="status" value="${status}"/>
                <input type="hidden" name="plantId" value="0">
                <input type="hidden" id="update" value="${update}"/>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">

    // var datepicker = $.fn.datepicker.noConflict(); // return $.fn.datepicker to previously assigned value
    // $.fn.bootstrapDP = datepicker;


    // $(function () {
    //     $('.date').datepicker({
    //         format: 'yyyy/mm/dd',
    //         autoclose: true,
    //         endDate: '+0d'
    //     });
    // });

    createJsonPlantMachineryObject();
    $('#btnSave').attr("disabled", true);
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);

    $(function () {
        $("#datePicker").datepicker(({
            dateFormat: 'yyyy',
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

    function loadPlantMachineryDetails() {
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadPlantMachineryDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=plantId]").val(data.plantId);
                    $("input[name=length]").val(data.length);
                    $("input[name=typeFuel]").val(data.typeFuel);
                    $("input[name=weidth]").val(data.weidth);
                    $("input[name=make]").val(data.make);
                    $("input[name=height]").val(data.height);
                    $("input[name=countryOrigin]").val(data.countryOrigin);
                    document.getElementById("isWarrantyId").selectedIndex = data.isWarranty;
                    $("input[name=model]").val(data.model);
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;
                    $("input[name=yearManufacture]").val(data.yearManufacture);
                    document.getElementById("isServiceAgreId").selectedIndex = data.isServiceAgre;
                    $("input[name=weight]").val(data.weight);
                    $("input[name=bayer]").val(data.bayer);
                    $("input[name=manufacture]").val(data.manufacture);
                    $("input[name=telephone]").val(data.telephone);
                    $("input[name=address]").val(data.address);
                    $("#btnSave").html('Update');
                } else {
                    $("input[name=plantId]").val("0");
                    $("input[name=length]").val("");
                    $("input[name=typeFuel]").val("");
                    $("input[name=weidth]").val("");
                    $("input[name=make]").val("");
                    $("input[name=height]").val("");
                    $("input[name=countryOrigin]").val("");
                    document.getElementById("isWarrantyId").selectedIndex = 0;
                    $("input[name=model]").val("");
                    document.getElementById("isInsuranceId").selectedIndex = 0;
                    $("input[name=yearManufacture]").val("");
                    document.getElementById("isServiceAgreId").selectedIndex = 0;
                    $("input[name=weight]").val("");
                    $("input[name=bayer]").val("");
                    $("input[name=manufacture]").val("");
                    $("input[name=telephone]").val("");
                    $("input[name=address]").val("");
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
                    window.location.href = "./plantMachinery";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./plantMachinery";
                })
            }
        }

    });

    function checkRegisterdYear() {
        var year = $("input[name=yearManufacture]").val();
        var assetId = $("#getId").val();
        if (year != "" && assetId > 0) {
            $.ajax({
                url: '${root}/maintenance/checkRegisterdYear',
                data: {year: year, assetId: assetId},
                success: function (data) {
                    if (data > 0) {
                        if (data == 1) {
                            msg = "Please select year, after the Asset registered year";
                        }
                        swal({
                            title: 'Warning',
                            text: msg,
                            type: 'warning',
                            timer: 2000
                        })
                        $("input[name=yearManufacture]").val("");
                    }

                }

            });
        }
    }

    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
            <%--var type = 5;--%>

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
        <%--var assetId = $("#assetCodeId").val();--%>
        <%--$.ajax({--%>
            <%--url: '${root}/data/checkAssetCodeValid/' + assetCode,--%>
            <%--success: function (data) {--%>
                <%--if (data != 0) {--%>
                    <%--$('#assetCodeUserId').removeClass('is-invalid');--%>
                    <%--$('#assetCodeId').val(data);--%>
                    <%--$('#btnSave').attr("disabled", false);--%>
                    <%--loadPlantMachineryDetails();--%>
                <%--} else {--%>
                    <%--$('#assetCodeId').val(0);--%>
                    <%--$('#btnSave').attr("disabled", true);--%>
                    <%--$('#assetCodeUserId').addClass('is-invalid'); // Giving a red border when invalid asset Code--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    var jsonArray = [];

    function createJsonPlantMachineryObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 5;

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
                    loadPlantMachineryDetails();
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