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

    <div class="row">
        <div class="col-md-12">
            <form name="vehicleForm" action="${root}/maintenance/saveVehicle" method="post">

                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                           aria-controls="home"
                           aria-selected="true">Motor Vehicle - Primary Data</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                           aria-controls="profile" aria-selected="false">Motor Vehicle - Secondary Data</a>
                    </li>
                </ul>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">&nbsp</div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                                    <div class="col-md-8">

                                        <input id="assetCodeTo" name="getName" style="height:35px;width:100%"
                                               type="text" autocomplete="off"
                                               class="form-control"
                                               placeholder="Enter 1st character"
                                               onblur="checkAssetCodeValid(),isEmpty()">
                                        <input id="getId" name="assetId" hidden>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Vehicle Class</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="50"
                                               id="vehicleClassId"
                                               name="vehicleClass"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Registration No.</div>
                                    <div class="col-md-8">
                                        <input type="text" required style="width: 100%" maxlength="12" id="regiNoId"
                                               name="regiNo"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Make</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="20" id="makeId"
                                               name="make"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Fuel Type</div>
                                    <div class="col-md-8">
                                        <select id="typeFuelId" required name="typeFuel"
                                                style="width: 100%">
                                            <option value="0">---SELECT---</option>
                                            <option value="1">Petrol</option>
                                            <option value="2">Diesel</option>
                                            <option value="3">Natural gas</option>
                                            <option value="4">Biodiesel</option>
                                            <option value="5">Hydrogen</option>
                                            <option value="6">Gasoline</option>
                                            <option value="7">Jet fuel</option>
                                            <option value="8">Other</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Model</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="50" id="model"
                                               name="model"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Country of Origin</div>
                                    <div class="col-md-8">
                                        <input type="text" required style="width: 100%" maxlength="50"
                                               id="countryOriginId"
                                               name="countryOrigin"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Year of Manufacture</div>
                                    <div class="col-md-7">
                                        <input type="number" class="date-own form-control"
                                               onkeypress="return isNumberOnly(event)" required maxlength="4"
                                               onblur="checkRegisterdYear()"
                                               style="height:93%;width: 100%; font-size: 14px" type="text"
                                               id="yearManufactuteId"
                                               min="1900" max="2099"
                                               name="yearManufactute">
                                        <script type="text/javascript">
                                            $('.date-own').datepicker({
                                                minViewMode: 2,
                                                format: 'yyyy'
                                            });
                                        </script>
                                        <%--<input type="text" autocomplete="off" required--%>
                                        <%--maxlength="4" id="yearPicker"--%>
                                        <%--style="width:100%; height: 35px"--%>
                                        <%--name="yearManufactute"/>--%>

                                    </div>

                                </div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Colour</div>
                                    <div class="col-md-8">
                                        <input type="text" required style="width: 100%" maxlength="20" id="colourId"
                                               name="colour"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Engine No.</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="20" id="engineNoId"
                                               name="engineNo"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Chassis No.</div>
                                    <div class="col-md-8">
                                        <input type="text" required style="width: 100%" maxlength="20" id="chassisNoId"
                                               name="chassisNo"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Seller Address</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="100"
                                               id="sellerAddressId"
                                               name="sellerAddress"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>


                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Seller Name</div>
                                    <div class="col-md-8">
                                        <input type="text" required style="width: 100%" maxlength="50" id="sellerNameId"
                                               name="sellerName"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Seller Telephone No.</div>
                                    <div class="col-md-7">
                                        <input type="text" required style="width: 100%" maxlength="20"
                                               id="sellerTelephoneNoId"
                                               name="sellerTelephoneNo"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                    </div>


                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="row">&nbsp</div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Cylinder Capacity</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="6" id="cyCapacytyId"
                                               onkeypress="return isNumberAndDecimalOnly(event)"
                                               name="cyCapacyty"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Tyre Size-Front</div>
                                    <div class="col-md-7">
                                        <input type="text" style="width: 100%" maxlength="10" id="tyreSizeFrontId"
                                               name="tyreSizeFront"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Taxation Class</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="50" id="taxClassId"
                                               name="taxClass"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Tyre Size - Rear</div>
                                    <div class="col-md-7">
                                        <input type="text" style="width: 100%" maxlength="20" id="tyreSizeRearId"
                                               name="tyreSizeRear"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">Registration Status</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="50" id="regiStatusId"
                                               name="regiStatus"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Registered Provincial Council</div>
                                    <div class="col-md-7">
                                        <input type="text" style="width: 100%" maxlength="100" id="regProvincialId"
                                               name="regProvincial"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-4" style="padding-top: 10px">No. of Pre. Owners</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="20" id="previOwnerNoId"
                                               onkeypress="return isNumberOnly(event)"
                                               name="previOwnerNo"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">First Registration Date</div>
                                    <div class="col-md-7">
                                        <div class='input-group date'>
                                            <input type='text' class="form-control" id="regFirstDaId"
                                                   style="height: 93%" name="regFirstDa"
                                                   onchange="checkregidterdDate($('input[name=regFirstDa]').val(),'regFirstDaId')"/>
                                            <span class="input-group-addon">
                                    <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                    <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    <span style="font-size:24px;color:#3e71fb"
                                          class="fa fa-calendar"></span>
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
                                    <div class="col-md-4" style="padding-top: 10px">Seating Capacity</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="3" id="seatCapacityId"
                                               onkeypress="return isNumberAndDecimalOnly(event)"
                                               name="seatCapacity"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Date - License & Eco Test</div>
                                    <div class="col-md-7">
                                        <div class='input-group date'>
                                            <input type='text' class="form-control" id="daLicenceId" style="height: 93%"
                                                   name="daLicence"
                                                   onchange="checkregidterdDate($('input[name=daLicence]').val(),'daLicenceId')"/>
                                            <span class="input-group-addon">
                                    <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                    <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    <span style="font-size:24px;color:#3e71fb"
                                          class="fa fa-calendar"></span>
                                    </span>
                                        </div>

                                        <%--<input type="text" style="width: 100%" maxlength="6" id="daLicenceId"--%>
                                        <%--name="daLicence"/>--%>
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
                                        <input type="text" style="width: 100%" maxlength="6" id="weightId"
                                               name="weight"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Interval of service</div>
                                    <div class="col-md-7">
                                        <input type="text" style="width: 100%" maxlength="10" id="serviceIntervalId"
                                               name="serviceInterval"/>
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
                                        <input type="text" style="width: 100%" maxlength="6" id="widthId"
                                               name="width"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Warranty</div>
                                    <div class="col-md-7">
                                        <select id="isWarrantyId"
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
                                    <div class="col-md-4" style="padding-top: 10px">Height</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="6" id="heightId"
                                               name="height"/>
                                    </div>
                                </div>

                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5" style="padding-top: 10px">Insurance</div>
                                    <div class="col-md-7">
                                        <select id="isInsuranceId"
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
                                    <div class="col-md-4" style="padding-top: 10px">Length</div>
                                    <div class="col-md-8">
                                        <input type="text" style="width: 100%" maxlength="6" id="lengthId"
                                               name="length"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-7"></div>

                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-5"></div>
                        <div class="col-md-6">
                            <div class="row" style="padding-top: 10px">
                                <div class="col-md-5"></div>
                                <div class="col-md-7">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <a class="btn btn-primary" style="width: 100%; font-size: 14px"
                                               href="${root}/maintenance/vehicle">New</a>
                                        </div>
                                        <div class="col-md-6" style="margin-top:-3px">
                                            <button type="submit" class="btn btn-primary" id="btnSave"
                                                    style="width: 100%; font-size: 14px">Save
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="0" name="vehicleId">

                </div>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="update" value="${update}"/>

<script type="text/javascript">

    // $(".chosen").chosen();
    //
    // loadAssetByType();

    createJsonVehicleObject();
    document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);


    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,

        });
    });


    $(function () {
        $("#yearPicker").datepicker(({

            changeMonth: false,

            showOtherMonths: false,
            showCalender: false,
            changeYear: true,
            yearRange: '2000:2050', // Optional Year Range
            dateFormat: 'yy',

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

    function loadVehicleDetails() {
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadVehicleDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=vehicleId]").val(data.vehicleId);
                    $("input[name=vehicleClass]").val(data.vehicleClass);
                    $("input[name=regiNo]").val(data.regiNo);
                    $("input[name=make]").val(data.make);
                    document.getElementById("typeFuelId").selectedIndex = data.typeFuel;
                    $("input[name=model]").val(data.model);
                    $("input[name=countryOrigin]").val(data.countryOrigin);
                    $("input[name=yearManufactute]").val(data.yearManufactute);
                    $("input[name=colour]").val(data.colour);
                    $("input[name=engineNo]").val(data.engineNo);
                    $("input[name=chassisNo]").val(data.chassisNo);
                    $("input[name=sellerAddress]").val(data.sellerAddress);
                    $("input[name=sellerName]").val(data.sellerName);
                    $("input[name=sellerTelephoneNo]").val(data.sellerTelephoneNo);
                    if (data.cyCapacyty != 0) {
                        $("input[name=cyCapacyty]").val(data.cyCapacyty);
                    }
                    else {
                        $("input[name=cyCapacyty]").val("0.00");
                    }
                    $("input[name=taxClass]").val(data.taxClass);
                    $("input[name=height]").val(data.height);
                    $("input[name=regiStatus]").val(data.regiStatus);
                    $("input[name=regProvincial]").val(data.regProvincial);
                    $("input[name=previOwnerNo]").val(data.previOwnerNo);
                    $("input[name=regFirstDa]").val(dateFormators(data.regFirstDa));
                    $("input[name=seatCapacity]").val(data.seatCapacity);
                    $("input[name=weight]").val(data.weight);
                    $("input[name=serviceInterval]").val(data.serviceInterval);
                    $("input[name=tyreSizeFront]").val(data.tyreSizeFront);
                    $("input[name=tyreSizeRear]").val(data.tyreSizeRear);
                    $("input[name=length]").val(data.length);
                    $("input[name=width]").val(data.width);
                    $("input[name=daLicence]").val(dateFormators(data.daLicence));
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;
                    document.getElementById("isWarrantyId").selectedIndex = data.isWarranty;
                    $("#btnSave").html('Update');

                } else {
                    $("input[name=vehicleId]").val("0");
                    $("input[name=vehicleClass]").val("");
                    $("input[name=regiNo]").val("");
                    $("input[name=make]").val("");
                    document.getElementById("typeFuelId").selectedIndex = 0;
                    $("input[name=model]").val("");
                    $("input[name=countryOrigin]").val("");
                    $("input[name=yearManufactute]").val("");
                    $("input[name=colour]").val("");
                    $("input[name=engineNo]").val("");
                    $("input[name=chassisNo]").val("");
                    $("input[name=sellerAddress]").val("");
                    $("input[name=sellerName]").val("");
                    $("input[name=sellerTelephoneNo]").val("");
                    $("input[name=cyCapacyty]").val("0.00");
                    $("input[name=taxClass]").val("");
                    $("input[name=height]").val("");
                    $("input[name=regiStatus]").val("");
                    $("input[name=regProvincial]").val("");
                    $("input[name=previOwnerNo]").val("0");
                    $("input[name=regFirstDa]").val("");
                    $("input[name=seatCapacity]").val("0");
                    $("input[name=weight]").val("");
                    $("input[name=serviceInterval]").val("");
                    $("input[name=tyreSizeFront]").val("");
                    $("input[name=tyreSizeRear]").val("");
                    $("input[name=length]").val("");
                    $("input[name=width]").val("");
                    $("input[name=daLicence]").val("");
                    document.getElementById("isInsuranceId").selectedIndex = 0;
                    document.getElementById("isWarrantyId").selectedIndex = 0;
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
                    window.location.href = "./vehicle";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./vehicle";
                })
            }
        }

    });

    <%--function loadAssetByType() {--%>
    <%--var type = 3; // Vehicle type Id( asset_type table)--%>
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


    function checkregidterdDate(dat, id) {
        var idName = id.valueOf();
        var concatIdName = "#" + idName;
        // var enteredValue = $(concatIdName).val().replace(/,/g, '');

        var type = $("#getId").val();

        if (dat != "" && type > 0) {
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
                        });
                        // $('#regFirstDaId').val("");
                        $(concatIdName).val("");
                    }
                }
            });
        }
    }

    function checkRegisterdYear() {
        var year = $("input[name=yearManufactute]").val();
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
                        $("input[name=yearManufactute]").val("");
                    }

                }

            });
        }
    }

    <%--function loadAssetByType() {--%>
    <%--setTimeout(function () {--%>
    <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
    <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
    <%--var type = 3;--%>

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
    <%--loadVehicleDetails();--%>
    <%--} else {--%>
    <%--$('#assetCodeId').val(0);--%>
    <%--$('#btnSave').attr("disabled", true);--%>
    <%--$('#assetCodeUserId').addClass('is-invalid'); // Giving a red border when invalid asset Code--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    $(function () {
        $(".datePicker").datepicker(({
            dateFormat: 'yy/mm/dd',
            changeMonth: true,
            changeYear: true,
            showOtherMonths: true,
            yearRange: 'c-200:c+200',
        }));

    });


    var jsonArray = [];

    function createJsonVehicleObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 3;

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
                    loadVehicleDetails();
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