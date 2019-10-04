<%--
  ~
  ~      Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
  ~       *  PROPRIETARY AND COPYRIGHT NOTICE.
  ~
  ~          This software product contains information which is proprietary to
  ~          and considered a trade secret MsSoftIT Solution .
  ~          It is expressly agreed that it shall not be reproduced in whole or part,
  ~          disclosed, divulged or otherwise made available to any third party directly
  ~          or indirectly.  Reproduction of this product for any purpose is prohibited
  ~          without written authorisation from the The MsSoftIT Solution
  ~          All Rights Reserved.
  ~
  ~          E-Mail mssoftit@gmail.com
  ~          URL : mssoftit.lk
  ~          Created By : Mahendra Sri Dayarathna

             Modification:  Add addComma function for adding comma seperator to currency Value by Dilusha on 2019/04/25
                            Add date validation function by Dilusha on 2019/04/26
                            Modification done for asset code loading function by Dilusha on 2019/06/17
  ~
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
        max-width: 307px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
        /* add padding to account for vertical scrollbar */
        padding-right: 20px;
    }
</style>

<div class="se-pre-con"></div>
<div class="col-md-12 page">
    <form name="landPrimaryForm" action="${root}/maintenance/saveLandDetail" method="POST">

        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                   aria-selected="true">Land - Primary Data</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                   aria-controls="profile" aria-selected="false">Land - Secondary Data</a>
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
                    <div class="col-md-5"></div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row" style="margin: 0;padding-top: 10px">
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
                            <div class="col-md-5" style="margin: 0;padding-top: 10px">Deed Type</div>
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
                            <div class="col-md-4" style="margin: 0;padding-top: 10px">Address Line 1</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="addressLine1Id" maxlength="20"
                                       name="lanAdd1"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="margin: 0;padding-top: 10px">Deed No.</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="deedNoId"
                                       onkeypress="return validateCharacter(event)" maxlength="6" name="deedNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="margin: 0;padding-top: 10px">Address Line 2</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="lanAdd2Id" maxlength="20"
                                       name="lanAdd2"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top:10px">Deed Signed Date</div>
                            <div class="col-md-7">
                                <div class='input-group date' style="width: 102%">
                                    <input type='text' required class="form-control"
                                           onchange="checkDeedSignedDate()" style="height: 100%"
                                           name="deedSignDate"/>
                                    <span class="input-group-addon">
                                        <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top:10px">Address Line 3</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="lanAdd3Id" maxlength="20"
                                       name="lanAdd3"/>
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
                                <div class='input-group date' style="width: 102%">
                                    <input type='text' required class="form-control" style="height: 100%"
                                           onchange="checkDeedRegisteredDate()"
                                           name="deedRegDate"/>
                                    <span class="input-group-addon">
                                            <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                        <span style="font-size:24px; color:#3e71fb" class="fa fa-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Address Line 4</div>
                            <div class="col-md-8">
                                <input type="text" required style="width: 100%" id="lanAdd4Id" maxlength="20"
                                       name="lanAdd4"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Deed Amount</div>
                            <div class="col-md-7">
                                <input type="text" class="numeric" required style="width: 100%" readonly
                                       onblur="return addComma(id)" onkeypress="return isNumberOnly(event)"
                                       maxlength="14" id="deedValueId" name="amount"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="row" style="margin-left: 12px;padding-top: 0px; ">
                                <legend style="width: 100px">Insurance</legend>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row" style="margin-top: -13px">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Extent</div>
                            <div class="col-md-7">
                                <input type="text" required style="width: 100%" id="extentId" maxlength="20"
                                       name="extent"/>
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

                </div>


            </div>


            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="row">&nbsp</div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row" style="margin: 0">
                            <legend>Attest Details</legend>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row" style="margin: 0">
                            <legend>Assessment Details</legend>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Attester Name</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" maxlength="100" id="attesterId" name="attester"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Assesment No.</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="assesmentNoId" maxlength="6"
                                       name="assesmentNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Attester Address</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" id="attesterAddId" maxlength="200"
                                       name="attesterAdd"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Assesment Value</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="assesmentValueId" maxlength="14"
                                       onblur="return addComma(id)" name="assesmentValue"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">&nbsp</div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row" style="margin: 0">
                            <legend>Plan Details</legend>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row" style="margin: 0">
                            <legend>Location</legend>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Survey Plan No.</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" id="surveyPlanNoId" maxlength="6"
                                       name="surveyPlanNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Land - Name</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="landNameId" maxlength="100" name="landName"/>
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
                                <input type="text" style="width: 100%" id="surveyNameId" maxlength="100"
                                       name="surveyName"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Situated</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="landSituatedId" maxlength="100"
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
                                <input type="text" style="width: 100%" id="surveyAddId" maxlength="200"
                                       name="surveyAdd"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Provincial Area</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="landPAreaId" maxlength="100"
                                       name="landPArea"/>
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
                                <input type="text" style="width: 100%" maxlength="20" id="surveyTelId"
                                       name="surveyTel"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Grama Niladari Division No.</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="graNilDiviNoId" maxlength="6"
                                       name="graNilDiviNo"/>
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
                                <div class='input-group date' style="width: 102%">
                                    <input type='text' class="form-control" style="height: 100%"
                                           name="surveyDate"/>
                                    <span class="input-group-addon">
                                              <%--<button style=height:25px;width:30px> <i style="font-size:15px;color:#3e71fb" class="fa fa-calendar"></i></button>--%>
                                            <span style="font-size:24px;color:#3e71fb" class="fa fa-calendar"></span>
                                        <%--<span class="glyphicon glyphicon-calendar" style="font-size:30px;color:red"></span>--%>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">Division No.</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="divisionNoId" maxlength="6"
                                       name="divisionNo"/>
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
                                <input type="text" style="width: 100%" id="lotNoId" maxlength="6" name="lotNo"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-5" style="padding-top: 10px">District</div>
                            <div class="col-md-7">
                                <input type="text" style="width: 100%" id="landDistrictId" maxlength="100"
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
                                <input type="text" style="width: 100%" id="landProvinceId" maxlength="100"
                                       name="landProvince"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row" style="margin: 0">
                            <legend>Seller Details</legend>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row" style="margin: 0">

                        </div>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Seller Name</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" id="sellerId" maxlength="100" name="seller"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">

                    </div>
                    <div class="col-md-1"></div>
                </div>


                <div class="row">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-4" style="padding-top: 10px">Seller Address</div>
                            <div class="col-md-8">
                                <input type="text" style="width: 100%" id="selAddId" maxlength="200" name="selAdd"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">

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
                                <a class="btn btn-primary" style="width: 100%; font-size: 14px; margin-top:3px;"
                                   href="${root}/maintenance/land">New</a>
                            </div>
                            <div class="col-md-6">
                                <button type="submit" id="btnSubmit" class="btn btn-primary"
                                        style="width: 100%; font-size: 14px">Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <input type="hidden" name="landId" value="0">
    </form>
</div>
<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="isEdit" value="${isEdit}"/>
<input type="hidden" id="update" value="${update}"/>

<script type="text/javascript">

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
                    window.location.href = "./land";
                })
            } else {
                swal({
                    title: 'Error !',
                    text: 'Land Added Fail !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./land";
                })
            }
        }

    });


    function loadLandDetails() {

        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadLandDetailsByAsset/' + assetId,
            success: function (data) {
                if (data.landId > 0) {
                    $("input[name=landId]").val(data.landId);
                    // $("input[name=deedType]").val(data.deedType);
                    document.getElementById("deedTypeId").selectedIndex = data.deedType;
                    $("input[name=deedNo]").val(data.deedNo);
                    $("input[name=deedSignDate]").val(dateFormators(data.deedSignDate));
                    $("input[name=deedRegDate]").val(dateFormators(data.deedRegDate));
                    $("input[name=lanAdd1]").val(data.lanAdd1);
                    $("input[name=lanAdd2]").val(data.lanAdd2);
                    $("input[name=lanAdd3]").val(data.lanAdd3);
                    $("input[name=lanAdd4]").val(data.lanAdd4);
                    $("input[name=amount]").val(data.amount);
                    $("input[name=extent]").val(data.extent);
                    $("input[name=comments]").val(data.comments);
                    $("input[name=attester]").val(data.attester);
                    $("input[name=attesterAdd]").val(data.attesterAdd);
                    $("input[name=seller]").val(data.seller);
                    $("input[name=selAdd]").val(data.selAdd);
                    $("input[name=graNilDiviNo]").val(data.graNilDiviNo);
                    $("input[name=divisionNo]").val(data.divisionNo);
                    $("input[name=assesmentNo]").val(data.assesmentNo);
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;
                    $("input[name=assesmentValue]").val(data.assesmentValueDisplay);
                    $("input[name=surveyPlanNo]").val(data.surveyPlanNo);
                    $("input[name=surveyName]").val(data.surveyName);
                    $("input[name=surveyAdd]").val(data.surveyAdd);
                    $("input[name=surveyTel]").val(data.surveyTel);
                    if (data.surveyDate != '') {
                        $("input[name=surveyDate]").val(dateFormators(data.surveyDate));
                    } else {
                        $("input[name=surveyDate]").val("");
                    }
                    $("input[name=lotNo]").val(data.lotNo);
                    $("input[name=landName]").val(data.landName);
                    $("input[name=landSituated]").val(data.landSituated);
                    $("input[name=landPArea]").val(data.landPArea);
                    $("input[name=landDistrict]").val(data.landDistrict);
                    $("input[name=landProvince]").val(data.landProvince);
                    $("#btnSubmit").html('Update');
                } else {
                    {
                        document.getElementById("deedTypeId").selectedIndex = 0;
                        $("input[name=landId]").val("0");
                        // $("input[name=deedType]").val("");
                        $("input[name=deedNo]").val("");
                        $("input[name=deedSignDate]").val("");
                        $("input[name=deedRegDate]").val("");
                        $("input[name=lanAdd1]").val("");
                        $("input[name=lanAdd2]").val("");
                        $("input[name=lanAdd3]").val("");
                        $("input[name=lanAdd4]").val("");
                        $("input[name=amount]").val(data.amount);
                        $("input[name=extent]").val("");
                        $("input[name=comments]").val("");
                        $("input[name=attester]").val("");
                        $("input[name=attesterAdd]").val("");
                        $("input[name=seller]").val("");
                        $("input[name=selAdd]").val("");
                        $("input[name=graNilDiviNo]").val("");
                        $("input[name=divisionNo]").val("");
                        $("input[name=assesmentNo]").val("");
                        $("input[name=assesmentValue]").val("0.00");
                        $("input[name=surveyPlanNo]").val("");
                        $("input[name=surveyName]").val("");
                        $("input[name=surveyAdd]").val("");
                        $("input[name=surveyTel]").val("");
                        $("input[name=surveyDate]").val("");
                        $("input[name=lotNo]").val("");
                        document.getElementById("isInsuranceId").selectedIndex = 0;
                        $("input[name=landName]").val("");
                        $("input[name=landSituated]").val("");
                        $("input[name=landPArea]").val("");
                        $("input[name=landDistrict]").val("");
                        $("input[name=landProvince]").val("");
                        $("#btnSubmit").html('Save');
                    }
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
            autoclose: true
        });
    });

    <%--function loadAssetByType() {--%>
    <%--var type = 1; // Land type Id( asset_type table)--%>
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
        var dat = $("input[name=deedSignDate]").val();
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
                            $("input[name=deedSignDate]").val("");
                        }
                    }

                });
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please Select Asset Id",
                type: 'warning',
                timer: 1000
            });
            $("input[name=deedSignDate]").val("");
        }
    }

    function checkDeedRegisteredDate() {
        var dateS = $("input[name=deedSignDate]").val();
        var dateR = $("input[name=deedRegDate]").val();
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
                    $("input[name=deedRegDate]").val("");
                }
            } else {
                swal({
                    title: 'Warning',
                    text: "Please select Deed Signed date",
                    type: 'warning',
                    timer: 1000
                });
                $("input[name=deedRegDate]").val("");
            }
        } else {
            swal({
                title: 'Warning',
                text: "Please select Asset Id",
                type: 'warning',
                timer: 1000
            });

            $("input[name=deedRegDate]").val("");
        }
    }

    <%--function loadAssetByType() {--%>
        <%--setTimeout(function () {--%>
            <%--var userBranch = '<%= session.getAttribute("userBranch") %>';--%>
            <%--var assetCodeIdval = $('input[name=assetCodeUser]').val().toUpperCase();--%>
            <%--var type = 1;--%>

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
                    <%--loadLandDetails();--%>
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
        var type = 1;

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
                    loadLandDetails();
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
