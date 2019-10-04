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
        padding-right: 20px;}
</style>

<!-- Paste this code after body tag -->
<div class="se-pre-con"></div>

    <div class="col-md-12 page">
        <div class="row" style="margin: 0">
            <legend>Fixtures And Fitting</legend>
        </div>

        <div class="row">
            <div class="col-md-12">
                <form name="fixturesAndFittingForm" action="${root}/maintenance/saveFixturesAndFitting" method="post">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-4" style="padding-top: 10px">Asset Code</div>
                                <div class="col-md-8">
                                    <%--<select class="chosen" id="assetCode" required onchange="loadFixturesAndFittingsDetailsByAsset()"--%>
                                            <%--name="assetId"--%>
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
                        <div class="col-md-1"></div>
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-4" style="padding-top: 10px">Weight</div>
                                <div class="col-md-8">
                                    <input type="text" required style="width: 100%" maxlength="6" id="weightId" name="weight"/>
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
                                    <input type="text" required style="width: 100%" maxlength="6" id="widthId"
                                           name="width"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-4" style="padding-top: 10px">Insurance</div>
                                <div class="col-md-8">
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
                                    <input type="text" required style="width: 100%" maxlength="6" id="lengthId"
                                           name="length"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-5">
                            <div class="row">
                                <div class="col-md-4" style="padding-top: 10px">Warranty</div>
                                <div class="col-md-8">
                                    <select id="isWarrantyId" name="isWarranty" style="width: 100%">
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
                                    <input type="text" required style="width: 100%" maxlength="6" id="heightId" name="height"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                        <div class="col-md-5"></div>
                        <div class="col-md-1"></div>
                    </div>

                    <div class="row">

                    </div>


                    <div class="row">
                        <div class="col-md-6"></div>
                        <div class="col-md-5">
                            <div class="row" style="padding-top: 10px">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <a class="btn btn-primary" style="width: 100%; font-size: 14px" href="${root}/maintenance/fixturesAndFitting">New</a>
                                </div>
                                <div class="col-md-4" style="margin-top:-3px">
                                    <button type="submit" class="btn btn-primary" id="btnSave" style="width: 100%; font-size: 14px" name="savebtn">Save</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <input type="hidden" name="fittingsId" value="0">
                </form>
            </div>
        </div>
    </div>


<input type="hidden" id="pageType" value="${pageType}"/>
<input type="hidden" id="status" value="${status}"/>
<input type="hidden" id="update" value="${update}"/>


<script type="text/javascript">

    createJsonFixtureFittingObject();
    $('#btnSave').attr("disabled", true);
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


    $(function () {
        var status = $("#status").val();
        var pageType = $("#pageType").val();
        var update=$("#update").val();

        if (update == 0) {
            message="Saved Successfully!!! ";
        } else {
            message="Updated Successfully!!! ";
        }
        if (pageType == 1) {
            if (status == 'true') {
                swal({
                    title: message,
                    type: 'success',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./fixturesAndFitting";
                })
            } else {
                swal({
                    title: 'Error',
                    text: 'Please try again !!',
                    type: 'error',
                    timer: 1000
                }).then(function () {
                    window.location.href = "./fixturesAndFitting";
                })
            }
        }

    });


    function loadFixturesAndFittingsDetailsByAsset() {
        var assetId = $("#getId").val();
        $.ajax({
            url: '${root}/maintenance/loadFixturesAndFittingsDetailsByAsset/' + assetId,
            success: function (data) {
                if (data != "") {
                    $("input[name=fittingsId]").val(data.fittingsId);
                    $("input[name=width]").val(data.width);
                    $("input[name=length]").val(data.length);
                    $("input[name=weight]").val(data.weight);
                    $("input[name=height]").val(data.height);
                    $("input[name=comments]").val(data.comments);
                    document.getElementById("isWarrantyId").selectedIndex = data.isWarranty;
                    document.getElementById("isInsuranceId").selectedIndex = data.isInsurance;
                    $("#btnSave").html('Update');

                } else {
                    $("input[name=fittingsId]").val("0");
                    $("input[name=width]").val("");
                    $("input[name=length]").val("");
                    $("input[name=weight]").val("");
                    $("input[name=height]").val("");
                    $("input[name=comments]").val("");
                    document.getElementById("isWarrantyId").selectedIndex = 0;
                    document.getElementById("isInsuranceId").selectedIndex = 0;
                    $("#btnSave").html('Save');

                }
            }
        });
    }

    var jsonArray = [];

    function createJsonFixtureFittingObject() {

        var userBranch = '<%= session.getAttribute("userBranch") %>';
        var type = 10;

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
                    loadFixturesAndFittingsDetailsByAsset();
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