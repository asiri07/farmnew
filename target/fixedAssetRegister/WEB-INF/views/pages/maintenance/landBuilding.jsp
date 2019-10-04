<%--
  Created by IntelliJ IDEA.
  User: Hashane
  Date: 2019-01-08
  Time: 11:44 AM

  Modification:  Add addComma function for adding comma seperator to currency Value by Dilusha on 2019/04/26
                 Add date validation function by Dilusha on 2019/04/26
                 Modification done for asset code loading function by Dilusha on 2019/06/18

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
</style>

<div class="se-pre-con"></div>
<div class="col-md-12 page">
    <form name="landBuildingForm" action="${root}/maintenance/saveLandBuilding" method="POST">

        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                   aria-controls="home" aria-selected="true">Land & Building - Primary Data</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                   aria-controls="profile" aria-selected="false">Land & Building - Secondary Data</a>
            </li>
        </ul>

        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="row">&nbsp</div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Asset Code</div>
                            <div class="col-md-7">

                                    <input id="assetCodeTo" name="getName" style="height:35px;width:100%" type="text" autocomplete="off"
                                           class="form-control"
                                           placeholder="Enter 1st character" onblur="checkAssetCodeValid(),isEmpty()">
                                    <input id="getId" name="assetId" hidden>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row" style="margin: 0; padding-top: 10px">
                            <legend>Deed Information</legend>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row" style="margin: 0;padding-top: 10px">
                            <legend>Location Address</legend>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Deed Type</div>
                            <div class="col-md-7">
                                <%--<input type="text" required style="width: 100%" id="deedTypeId" maxlength="20" name="deedType"/>--%>
                                <select required style="width: 100%" id="deedTypeId" name="deedType">
                                    <option value="0"> ---SELECT---</option>
                                    <option value="1">Mortgage</option>
                                    <option value="2">Transfer</option>
                                    <option value="3">Gift</option>
                                    <option value="4">Other</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Address Line 1</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="20" id="buildingAddress1Id"
                                       name="buildingAddress1"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Deed No.</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%"
                                       onkeypress="return validateCharacter(event)" id="deedNoId" maxlength="6"
                                       name="deedNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Address Line 2</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="20" id="buildingAddress2Id"
                                       name="buildingAddress2"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Deed Signed Date</div>
                            <div class="col-md-7">
                                <div class='input-group date' style="width:100%">
                                    <input type='text'  class="form-control"
                                           onchange="checkDeedSignedDate();loadSignedDate()"
                                           name="deedSignedDate" style="height:100%"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Address Line 3</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="20" id="buildingAddress3Id"
                                       name="buildingAddress3"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Deed Registered Date</div>
                            <div class="col-md-7">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' required class="form-control"
                                           name="deedRegisterDate" onchange="checkDeedRegisteredDate()"
                                           style="height: 100%;"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Address Line 4</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" maxlength="20" id="buildingAddress4Id"
                                       name="buildingAddress4"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Value of the Building</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="deedValueId" maxlength="14" readonly
                                       onblur="return addComma(id)" onkeypress="return isNumberAndDecimalOnly(event)"
                                       name="amount"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Extent</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="extentId" maxlength="20"
                                       name="extent"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>&nbsp</div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">No. of Stories</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="3"
                                       onkeypress="return isNumberOnly(event)" id="buildingStoriedId"
                                       name="buildingStoried"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Assesment No.</div>
                            <div class="col-md-7">
                                <input type="text" assessmentNo style="width: 100%" maxlength="6" id="assessmentNoId"
                                       name="assessmentNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Plan No.</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="6" id="buildingPlanNoId"
                                       name="buildingPlanNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Assesment Value</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="14" id="assessementValueId"
                                       value="0.00"
                                       onblur="return addComma(id)" onkeypress="return isNumberAndDecimalOnly(event)"
                                       name="assessementValue"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">C.O.C No.</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="6" id="cocNoId" name="cocNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Attester of Deed</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="attesterId" name="attester"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Lot No.</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="6" id="lotNoId" name="lotNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Address of Attester</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="200" id="attesterAddressId"
                                       name="attesterAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Insuarance</div>
                            <div class="col-md-8">
                                <select id="isInsuranceId"
                                        name="isInsurance" style="width: 100%">
                                    <option value="0">No</option>
                                    <option value="1">Yes</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Name of the Seller</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="sellerNameId"
                                       name="seller"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Address of the Seller</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="sellerAddressId"
                                       name="sellerAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">&nbsp</div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row" style="margin: 0">
                            <legend>Survey Details</legend>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row" style="margin: 0">
                            <legend>Location</legend>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Survey Plan No.</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="6" id="surveyPlanNoId"
                                       name="surveyPlanNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Building - Name</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="landNameId" name="landName"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Survey Name</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="100" id="surveyNameId"
                                       name="surveyName"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Situated</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="landSituatedId"
                                       name="landSituated"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Survey Address</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="200" id="surveyAddressId"
                                       name="surveyAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Provincial Area</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="landProvincalAreaId"
                                       name="landProvincalArea"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Survey Telephone</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="20" id="surveyTelephoneId"
                                       name="surveyTelephone"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Grama Niladari Division No.</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="6" id="graNilDivNoId"
                                       name="graNilDivNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Date Of Survey</div>
                            <div class="col-md-8">
                                <div class='input-group date' style="width: 100%">
                                    <input type='text' class="form-control" style="height: 100%"
                                           name="surveyDate"/>
                                    <span class="input-group-addon">
                                             <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Division No.</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="6" id="divisionNoId"
                                       name="divisionNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">District</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="landDistrictId"
                                       name="landDistrict"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">

                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Province</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" maxlength="100" id="landProvinceId"
                                       name="landProvince"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </div>

        <div class="row">&nbsp</div>
        <div class="row">
            <div class="col-md-6"></div>
            <div class="col-md-5">
                <div class="row">
                    <div class="col-md-4" style="margin: 0;padding-top: 10px"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-6">
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px;  margin-top: 3px"
                                   href="${root}/maintenance/landBuilding">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" id="btnSubmit" class="btn btn-primary"
                                        style="width: 100%; font-size: 14px;">Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <input type="hidden" name="landBuildingID" value="0">
    </form>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>
<input type="hidden" id="update" value="${update}"/>

<script type="text/javascript">

//    $(".chosen").chosen();
//
//    loadAssetByType();

createJsonOfficeEquipmentObject();
$('#btnSubmit').attr("disabled", true);
document.getElementById('assetCodeTo').setAttribute('maxlength', ASSET_CODE_LENGTH);


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
                    window.location.href = "./landBuilding";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./landBuilding";
                })
            }
        }

    });


    function loadLandBuildingDetails() {
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadLandBuildingDetailsByAsset/' + assetId,
            success: function (data) {
                if (data.landBuildingID > 0) {
                    if (data.deedNo == null) {
                        $("#btnSubmit").html('Save');
                    } else {
                        $("#btnSubmit").html('Update');
                    }
                    document.getElementById("deedTypeId").selectedIndex = data.deedType;
                    $("input[name=landBuildingID]").val(data.landBuildingID);
                    // $("input[name=deedType]").val(data.deedType);
                    $("input[name=deedNo]").val(data.deedNo);
                    $("input[name=deedSignedDate]").val(dateFormators(data.deedSignedDate));
                    $("input[name=deedRegisterDate]").val(dateFormators(data.deedRegisterDate));
                    $("input[name=buildingAddress1]").val(data.buildingAddress1);
                    $("input[name=buildingAddress2]").val(data.buildingAddress2);
                    $("input[name=buildingAddress3]").val(data.buildingAddress3);
                    $("input[name=buildingAddress4]").val(data.buildingAddress4);
                    $("input[name=amount]").val(data.amount);
                    $("input[name=extent]").val(data.extent);
                    $("input[name=comments]").val(data.comments);
                    $("input[name=attester]").val(data.attester);
                    $("input[name=attesterAddress]").val(data.attesterAddress);
                    $("input[name=seller]").val(data.seller);
                    $("input[name=sellerAddress]").val(data.sellerAddress);
                    $("input[name=graNilDivNo]").val(data.graNilDivNo);
                    $("input[name=divisionNo]").val(data.divisionNo);
                    $("input[name=assessmentNo]").val(data.assessmentNo);
                    $("input[name=assessementValue]").val(data.assesmentValueDisplay);
                    $("input[name=surveyPlanNo]").val(data.surveyPlanNo);
                    $("input[name=surveyName]").val(data.surveyName);
                    $("input[name=surveyAddress]").val(data.surveyAddress);
                    $("input[name=surveyTelephone]").val(data.surveyTelephone);
                    if (data.surveyDate != '') {
                        $("input[name=surveyDate]").val(dateFormators(data.surveyDate));
                    } else {
                        $("input[name=surveyDate]").val("");
                    }
                    $("input[name=lotNo]").val(data.lotNo);
                    $("input[name=landName]").val(data.landName);
                    $("input[name=landSituated]").val(data.landSituated);
                    $("input[name=landProvincalArea]").val(data.landProvincalArea);
                    $("input[name=landDistrict]").val(data.landDistrict);
                    $("input[name=landProvince]").val(data.landProvince);
                    $("input[name=isInsurance]").val(data.isInsurance);
                    $("input[name=buildingStoried]").val(data.buildingStoried);
                    $("input[name=buildingPlanNo]").val(data.buildingPlanNo);
                    $("input[name=cocNo]").val(data.cocNo);

                } else {
                    $("#btnSubmit").html('Save');
                    $("input[name=landBuildingID]").val("0");
                    document.getElementById("deedTypeId").selectedIndex = 0;
                    // $("input[name=deedType]").val("");
                    $("input[name=deedNo]").val("");

                    $("input[name=deedSignedDate]").val("");
                    $("input[name=deedRegisterDate]").val("");
                    $("input[name=buildingAddress1]").val("");
                    $("input[name=buildingAddress2]").val("");
                    $("input[name=buildingAddress3]").val("");
                    $("input[name=buildingAddress4]").val("");
                    $("input[name=amount]").val(data.amount);
                    $("input[name=extent]").val("");
                    $("input[name=comments]").val("");
                    $("input[name=attester]").val("");
                    $("input[name=attesterAddress]").val("");
                    $("input[name=seller]").val("");
                    $("input[name=sellerAddress]").val("");
                    $("input[name=graNilDivNo]").val("");
                    $("input[name=divisionNo]").val("");
                    $("input[name=assessmentNo]").val("");
                    $("input[name=assessementValue]").val("0.00");
                    $("input[name=surveyPlanNo]").val("");
                    $("input[name=surveyName]").val("");
                    $("input[name=surveyAddress]").val("");
                    $("input[name=surveyTelephone]").val("");
                    $("input[name=surveyDate]").val("");
                    $("input[name=lotNo]").val("");
                    $("input[name=landName]").val("");
                    $("input[name=landSituated]").val("");
                    $("input[name=landProvincalArea]").val("");
                    $("input[name=landDistrict]").val("");
                    $("input[name=landProvince]").val("");
                    document.getElementById("isInsuranceId").selectedIndex = 0;
                    $("input[name=buildingStoried]").val("");
                    $("input[name=buildingPlanNo]").val("");
                    $("input[name=cocNo]").val("");

                }
            }
        });

    }

    $(window).load(function () {
// Animate loader off screen
        $(".se-pre-con").fadeOut("slow");
    });


    $(function () {
        $('.date').datepicker({
            format: 'yyyy/mm/dd',
            autoclose: true,
        });
    });

    <%--function loadAssetByType() {--%>
    <%--var type = 2; // Land & Building type Id( asset_type table)--%>
    <%--$.ajax({--%>
    <%--url: '${root}/maintenance/loadAssetByType/' + type,--%>
    <%--success: function (data) {--%>
    <%--for (var i = 0; i < data.length; i++) {--%>
    <%--$("#assetCodeId").append(--%>
    <%--"<option value = '" + data[i][0] + "'>" + data[i][1] + " - " + data[i][2]--%>
    <%--+ "</option>");--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    function checkDeedSignedDate() {
        var dat = $("input[name=deedSignedDate]").val();
        var assetId = $("#getId").val();
        if (assetId > 0) {
            if (dat != "") {
                var date = formatDate(dat);
                $.ajax({
                    url: '${root}/maintenance/checkDeedSignedDate',
                    data: {date: date, assetId: assetId},
                    success: function (data) {
                        if (data > 0) {
                            var msg;
                            switch (data) {
                                case 1:
                                    msg = "Please change Deed Signed date";
                                    break;
                                case 2:
                                    msg = "Please select date, after Asset registered date";
                                    break;
                            }
                            swal({
                                title: 'Warning',
                                text: msg,
                                type: 'warning',
                                timer: 1000
                            })
                            $("input[name=deedSignedDate]").val("");
                        }
                    }
                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
            });
            $("input[name=deedSignedDate]").val("");
        }
    }

    function checkDeedRegisteredDate() {
        var dateS = $("input[name=deedSignedDate]").val();
        var dateR = $("input[name=deedRegisterDate]").val();
        var assetId = $("#getId").val();
        if (assetId > 0) {
            if (dateS != "") {
                var dateSigned = formatDate(dateS);
                var dateRegistered = formatDate(dateR);
                if (dateRegistered < dateSigned) {
                    swal({
                        title: 'Warning',
                        text: "Select Date, After or equal Deed Signed Date",
                        type: 'warning',
                        timer: 1000
                    });
                    $("input[name=deedRegisterDate]").val("");
                }
            } else {
                swal({
                    title: 'Warning',
                    text: "Please select Deed Signed date",
                    type: 'warning',
                    timer: 1000
                });
                $("input[name=deedRegisterDate]").val("");
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please select Asset Id",
                type: 'warning',
                timer: 1000
            });

            $("input[name=deedRegisterDate]").val("");
        }
    }


    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
            <%--var type = 2;--%>

            <%--if (assetCodeIdval == '') {--%>
                <%--assetCodeIdval = 0;--%>
            <%--}--%>

            $.ajax({
                url: '${root}/data/loadAssetByAssetCode/' + userBranch + '/' + assetCodeIdval + '/' + type,
                success: function (data) {
                    assetCodeIdval = assetCodeIdval == "0" ? "" : assetCodeIdval;
                    $("#listasset").html('<input  autoComplete="off" list="divAssetList" style="font-size: 15px; height: 35px"\n' +
                        '               onkeypress="loadAssetByType()"' +
                        '               onblur="checkAssetCodeValid()" value="' + assetCodeIdval + '" ' +
                        '               id="assetCodeUserId" name="assetCodeUser" placeholder="Search.." class="form-control "/>');
                    $("#divAssetList").html('');
                    var ten = 100;
                    if (data.length < 100) {
                        ten = data.length;
                    }
                    for (var i = 0; i < ten; i++) {
                        $("#divAssetList").append("<option selected value = '" + data[i][1] + "'>" + data[i][1] + " - " + data[i][2] + "</option>");
                    }
                    $('#assetCodeUserId').putCursorAtEnd();
                }
            });
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
                    <%--loadLandBuildingDetails();--%>
                <%--} else {--%>
                    <%--$('#assetCodeId').val(0);--%>
                    <%--$('#btnSave').attr("disabled", true);--%>
                    <%--$('#assetCodeUserId').addClass('is-invalid'); // Giving a red border when invalid asset Code--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--jQuery.fn.putCursorAtEnd = function () {--%>
        <%--return this.each(function () {--%>
            <%--var $el = $(this),--%>
                <%--el = this;--%>
            <%--if (!$el.is(":focus")) {--%>
                <%--$el.focus();--%>
            <%--}--%>
            <%--if (el.setSelectionRange) {--%>
                <%--var len = $el.val().length * 2;--%>
                <%--setTimeout(function () {--%>
                    <%--el.setSelectionRange(len, len);--%>
                <%--}, 1);--%>
            <%--} else {--%>
                <%--$el.val($el.val());--%>
            <%--}--%>
            <%--this.scrollTop = 999999;--%>
        <%--});--%>
    <%--};--%>



    var jsonArray = [];

    function createJsonOfficeEquipmentObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 2;

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

                    $('#btnSubmit').attr("disabled", false);
                    $('#assetCodeTo').css('border-color', '#aaaaaa');
                    loadLandBuildingDetails();
                } else {
                    $('#btnSubmit').attr("disabled", true);
                    $('#assetCodeTo').css('border-color', 'red');
                }
            }
        });
    }


    function isEmpty() {

        if (document.getElementById("assetCodeTo").value == '') {
            $('#assetCodeTo').css('border-color', '#aaaaaa');
            $('#form input[type="text"]').val('');


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


